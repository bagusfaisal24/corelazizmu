package portal.security.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import portal.security.model.OauthRefreshAccessTokenModel;

@Repository
@Transactional
public interface OauthRefreshTokenRepositories extends CrudRepository<OauthRefreshAccessTokenModel, String> {

    @Query(value = "select * from oauth_refresh_token where CONVERT(token USING utf8) like concat('%',?1,'%')", nativeQuery = true)
    OauthRefreshAccessTokenModel findToken(String refreshToken);
}
