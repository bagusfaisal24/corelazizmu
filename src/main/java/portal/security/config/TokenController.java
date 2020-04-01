package portal.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import portal.core.exception.DataNotFoundException;
import portal.core.model.OkResponse;
import portal.security.model.OauthAccessTokenModel;
import portal.security.model.OauthRefreshAccessTokenModel;
import portal.security.repository.OauthAccessTokenRepositories;
import portal.security.repository.OauthRefreshTokenRepositories;

@Controller
public class TokenController {

    private final OauthAccessTokenRepositories oauthAccessTokenRepo;
    private final OauthRefreshTokenRepositories oauthRefreshTokenRepo;

    @Autowired
    public TokenController(OauthAccessTokenRepositories oauthAccessTokenRepo, OauthRefreshTokenRepositories oauthRefreshTokenRepo) {
        this.oauthAccessTokenRepo = oauthAccessTokenRepo;
        this.oauthRefreshTokenRepo = oauthRefreshTokenRepo;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/revoke/access-token/{tokenId}")
    @ResponseBody
    public OkResponse revokeToken(@PathVariable String tokenId) {
        OauthAccessTokenModel oauthAccessToken = oauthAccessTokenRepo.findToken(tokenId);
        if (oauthAccessToken == null) {
            throw new DataNotFoundException("token tidak ditemukan atau expired");
        } else {
            oauthAccessTokenRepo.delete(oauthAccessToken);
            return new OkResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/revoke/refresh-token/{tokenId}")
    @ResponseBody
    public OkResponse revokeRefreshToken(@PathVariable String tokenId) {
        OauthRefreshAccessTokenModel oauthRefreshAccessTokenModel = oauthRefreshTokenRepo.findToken(tokenId);
        if (oauthRefreshAccessTokenModel == null) {
            throw new DataNotFoundException("token tidak ditemukan atau expired");
        } else {
            oauthRefreshTokenRepo.delete(oauthRefreshAccessTokenModel);
            return new OkResponse();
        }
    }

}
