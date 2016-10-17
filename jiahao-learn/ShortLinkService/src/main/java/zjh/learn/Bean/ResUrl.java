package zjh.learn.Bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

/**
 * Created by jiahao on 16-8-1.
 */

@Document(collection = "resUrl")
public class ResUrl {

    @Id
    private String id;
    private String longUrl;
    private String shortUrl;

    public ResUrl(String shortUrl, String longUrl) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }

    //region getter and setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
    //endregion

    /**
    * 使用新传入的shortUrl数据对自己数据进行更新，如果是空的就不更新
    */
    public ResUrl updateResUrl(ResUrl resUrlNew) {
        this.setShortUrl(StringUtils.isEmpty(resUrlNew.getShortUrl()) ? this.getShortUrl() : resUrlNew.getShortUrl());
        this.setLongUrl(StringUtils.isEmpty(resUrlNew.getLongUrl()) ? this.getLongUrl() : resUrlNew.getLongUrl());
        return this;
    }
}
