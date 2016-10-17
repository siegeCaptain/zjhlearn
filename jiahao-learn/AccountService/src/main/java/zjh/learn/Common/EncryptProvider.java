package zjh.learn.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import zjh.learn.Bean.AccountConfig;
import zjh.learn.Utils.StringEncrypt;

/**
 * Created by Air.Y on 2016/5/3.
 */
@Service
@EnableConfigurationProperties({AccountConfig.class})
public class EncryptProvider {

    private final AccountConfig accountConfig;

    @Autowired
    public EncryptProvider(AccountConfig accountConfig) {
        this.accountConfig = accountConfig;
    }

    public String encryptPassword(String password) {
        String saltPassword = salt(password == null ? "" : password);
        return StringEncrypt.getInstance(StringEncrypt.EncryptType.SHA_256).encodeBase64(saltPassword);
    }

    private String salt(String password) {
        String salt = getSalt();
        if (salt.indexOf("${password}") > -1)
            return salt.replace("${password}", password);
        else
            return salt + password;
    }

    private String getSalt() {
        String salt = accountConfig != null ? accountConfig.getUserPasswordSalt() : null;
        return StringUtils.isEmpty(salt) ? SALT_DEFAULT : salt;
    }

    private static final String SALT_DEFAULT = "@zjh950315{${password}}";
}
