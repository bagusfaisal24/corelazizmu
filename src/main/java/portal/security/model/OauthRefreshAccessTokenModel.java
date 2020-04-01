package portal.security.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_refresh_token")
@Setter
@Getter
public class OauthRefreshAccessTokenModel {

    @Id
    @Column(name = "token_id")
    private String tokenId;

}
