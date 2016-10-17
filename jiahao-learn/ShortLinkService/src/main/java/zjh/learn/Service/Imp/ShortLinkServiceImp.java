package zjh.learn.Service.Imp;

import zjh.learn.Bean.ResUrl;
import zjh.learn.Common.CMyEncrypt;
import zjh.learn.Repository.ShortLinkRepository;
import zjh.learn.Service.ShortLinkService;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by jiahao on 16-8-1.
 */
@Service
public class ShortLinkServiceImp implements ShortLinkService {

    private final ShortLinkRepository shortLinkRepository;

    @Autowired
    public ShortLinkServiceImp(ShortLinkRepository shortLinkRepository) {
        this.shortLinkRepository = shortLinkRepository;
    }

    @Override
    public String shortUrl(String longUrl) {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY
        String key = "jiahao";
        // 要使用生成 URL 的字符
        String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"

        };
        // 对传入网址进行 MD5 加密
        String sMD5EncryptResult = (new CMyEncrypt()).md5(key + longUrl);
        String hex = sMD5EncryptResult;

        String[] shortUrls = new String[4];
        for (int i = 0; i < 4; i++)
        {

            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);

            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用
            // long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            for (int j = 0; j < 6; j++)
            {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加
                outChars += chars[(int) index];
                // 每次循环按位右移 5 位
                lHexLong = lHexLong >> 5;
            }
            // 把字符串存入对应索引的输出数组
            shortUrls[i] = outChars;
        }

        //将长短链接映射保存在数据库
        String shortUrl = "http://jiahao/" + shortUrls[(new Random()).nextInt(3)];
        ResUrl resUrl = new ResUrl(shortUrl,longUrl);
        resUrl = combineResUrlExists(resUrl);
        shortLinkRepository.save(resUrl);
        return shortUrl;
    }

    @Override
    public String shortUrlByHashids(String longUrl) {
        Hashids hashids = new Hashids("jiahao");
        return hashids.encodeHex(longUrl);
    }

    @Override
    public String getLongLink(String shortUrl) {
        ResUrl resUrl = shortLinkRepository.findByShortUrl(shortUrl);
        return resUrl.getLongUrl();
    }

    /**
     * 将需要更新的ResUrl信息去数据库中查询，如果已经存在，则取出该数据并且进行合并
     * 如果不存在则返回原对象
     */
    public ResUrl combineResUrlExists(ResUrl resUrlNew) {
        ResUrl resUrlExists = shortLinkRepository.findByLongUrl(resUrlNew.getLongUrl());
        return combineResUrlExists(resUrlNew, resUrlExists);
    }
    public ResUrl combineResUrlExists(ResUrl resUrlNew, ResUrl resUrlExists) {
        if (resUrlExists != null && resUrlExists.getId() != null) {
            resUrlExists.updateResUrl(resUrlNew);
            return resUrlExists;
        }
        return resUrlNew;
    }
}
