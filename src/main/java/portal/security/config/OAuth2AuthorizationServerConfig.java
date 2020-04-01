package portal.security.config;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import portal.util.SmartLoader;

import java.io.IOException;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${app.security.keyPass:password}")
    private String keyPass;

    @Value("${app.security.storePass:password}")
    private String storePass;

    @Value("${app.security.alias:kunci}")
    private String alias;

    @Value("${app.report.secret:/templates/secret/kunci.jks}")
    @Setter
    private String jksFile;

    @Value("${app.report.public:/templates/secret/public.txt}")
    @Setter
    private String publicKey;

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetails;
    private final JDBCTokenConfig jdbcTokenConfig;

    @Autowired
    public OAuth2AuthorizationServerConfig(AuthenticationManager authManager, JDBCTokenConfig jdbcTokenConfig,
                                           @Qualifier("userDetailsService") UserDetailsService userDetails) {
        this.authenticationManager = authManager;
        this.jdbcTokenConfig = jdbcTokenConfig;
        this.userDetails = userDetails;
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("gigy")
                .secret("secret")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read", "write")
                .accessTokenValiditySeconds(86400);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .userDetailsService(userDetails)
                .tokenStore(jdbcTokenConfig.tokenStore())
                .accessTokenConverter(jwtTokenEnhancer())
                .authenticationManager(authenticationManager);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(jdbcTokenConfig.tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        Resource jks = SmartLoader.smartLoad(jksFile);
        Resource resourcePublic = SmartLoader.smartLoad(publicKey);
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(jks, keyPass.toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(alias));
        String strPublicKey;
        try {
            strPublicKey = (resourcePublic.getInputStream()).toString();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        converter.setVerifierKey(strPublicKey);
        return converter;
    }
}

