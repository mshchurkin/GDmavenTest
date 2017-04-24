package mshchurkin.Backend;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class URLRequestMaker {

    APIWorker aw = APIWorker.getInstance();

    String urlString=null;
    Map<String,String> params=null;
    Map<String,String> headers=null;
    URL url=null;
    StringBuilder sb=null;

    public StringBuilder getSb() throws IOException {

        if(params!=null) {
            if (params.size() != 0) {
                urlString=urlString+"?";
                for (Map.Entry<String, String> param : params.entrySet()) {
                    String property = param.getKey();
                    String value = param.getValue();
                    urlString=urlString+property+"="+value+"&";
                }
                urlString=urlString.substring(0,urlString.length()-1);
            }
        }
        url = new URL(urlString);

        //заголовки
        Map<String,String> headers=new HashMap<>();

        sb = aw.executeGet(url, headers);
        return sb;
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void setSb(StringBuilder sb) {
        this.sb = sb;
    }
}
