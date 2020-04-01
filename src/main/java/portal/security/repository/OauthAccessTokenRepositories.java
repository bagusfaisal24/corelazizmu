package portal.security.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import portal.security.model.OauthAccessTokenModel;

@Repository
@Transactional
public interface OauthAccessTokenRepositories extends CrudRepository<OauthAccessTokenModel, String> {

    @Query(value = "select * from oauth_access_token where CONVERT(token USING utf8) like concat('%',?1,'%')", nativeQuery = true)
    OauthAccessTokenModel findToken(String accessToken);
}
