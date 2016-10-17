package zjh.learn.Controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import zjh.learn.Repository.TokenRepository;
import zjh.learn.Service.TokenService;
import zjh.learn.bean.TokenDto;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by jiahao on 16-8-10.
 */
@RestController
@RequestMapping(value = "/api/token")
public class TokenController {

    private final HttpServletResponse response;
    private final TokenRepository tokenRepository;
    private final TokenService tokenService;

    @Autowired
    public TokenController(HttpServletResponse response,
                           TokenRepository tokenRepository,
                           TokenService tokenService) {
        this.response = response;
        this.tokenRepository = tokenRepository;
        this.tokenService = tokenService;
    }

    //region 验证Token有效性
    @RequestMapping(method = RequestMethod.GET, value = "/{authCode}")
    @ApiOperation(value = "验证Token有效性，返回Token信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Token有效，返回Token信息"),
            @ApiResponse(code = 404, message = "Token无效或已过期")
    })
    public TokenDto getToken(
            @ApiParam(value = "authCode", required = true) @PathVariable("authCode") String authCode) {
        TokenDto token = tokenRepository.getByAuthCode(authCode);
        if (token == null || token.getExpireTime() < new Date().getTime()) {
            return TokenDto.EMPTY;
        }
        return token;
    }
    //endregion

    //region 根据UserID和客户端访问IP创建Token
    @RequestMapping(method = RequestMethod.POST, value = "/{userId}")
    @ApiOperation(value = "根据UserID和客户端访问IP创建Token")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回Token信息"),
            @ApiResponse(code = 404, message = "user不存在")
    })
    public TokenDto putToken(
            @ApiParam(value = "userId", required = true) @PathVariable("userId") String userId,
            @ApiParam(value = "ip", required = true) String ip) {
        TokenDto token = tokenService.putToken(userId, ip);
        if (token == null) {
            response.setStatus(404);
            return null;
        }
        return token;
    }
    //endregion

    //region 根据AuthCode删除Token
    @RequestMapping(method = RequestMethod.GET, value = "/remove/{authCode}")
    @ApiOperation(value = "根据AuthCode删除Token")
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除Token成功"),
            @ApiResponse(code = 404, message = "删除Token失败，Token不存在！")
    }
    )
    public boolean removeToken(
            @ApiParam(value = "authCode",required = true) @PathVariable("authCode") String authCode){
        return tokenService.removeTokenByAuthCode(authCode);
    }
    //endregion
}
