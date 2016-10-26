package zjh.learn.Controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zjh.learn.Dtos.ValidateInputDto;
import zjh.learn.Dtos.ValidateOutputDto;
import zjh.learn.Repository.TokenRepository;
import zjh.learn.Repository.UserRepository;
import zjh.learn.Service.TokenService;
import zjh.learn.bean.TokenDto;
import zjh.learn.bean.User;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by jiahao on 16-8-10.
 */
@RestController
@RequestMapping(value = "/api/token")
public class TokenController {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Autowired
    public TokenController(TokenRepository tokenRepository,
                           UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    //region 验证Token有效性
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public ValidateOutputDto validate(@RequestBody ValidateInputDto inputDto) {
        TokenDto tokenDto = tokenRepository.getByAuthCode(inputDto.getKey());
        ValidateOutputDto output = new ValidateOutputDto();
        if (tokenDto == null || tokenDto.getExpireTime() < new Date().getTime()) {
            output.setStatus(0);
            return output;
        }
        User user = userRepository.findOne(tokenDto.getUserId());
        output.setStatus(1);
        output.setPhone(user.getPhone());
        output.setOpenId(user.getOpenId());
        output.setUserId(tokenDto.getUserId());
        return output;
    }
    //endregion

    //region 根据UserID和客户端访问IP创建Token
//    @RequestMapping(method = RequestMethod.POST, value = "/{userId}")
//    public TokenDto putToken(String userId, String ip) {
//        TokenDto token = tokenService.putToken(userId, ip);
//        if (token == null) {
//            response.setStatus(404);
//            return null;
//        }
//        return token;
//    }
    //endregion
}
