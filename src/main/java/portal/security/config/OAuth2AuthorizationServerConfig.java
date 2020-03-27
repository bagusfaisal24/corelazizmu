package portal.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final TokenStore tokenStore;
    private final UserDetailsService userDetails;

    @Autowired
    public OAuth2AuthorizationServerConfig(AuthenticationManager authManager, TokenStore tokenStore,
                                           @Qualifier("userDetailsService") UserDetailsService userDetails) {
        this.authenticationManager = authManager;
        this.tokenStore = tokenStore;
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
                .tokenStore(this.tokenStore)
                .authenticationManager(authenticationManager);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(this.tokenStore);
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }
}

