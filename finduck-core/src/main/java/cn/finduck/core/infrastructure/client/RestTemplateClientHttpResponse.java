package cn.finduck.core.infrastructure.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import java.io.InputStream;
import java.io.Serializable;

/**
 * @author luocy
 * @description RestTemplate响应
 * @create 2021-04-28
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class RestTemplateClientHttpResponse implements ClientHttpResponse, Serializable {

    private static final long serialVersionUID = 1208137530850697215L;

    private HttpStatus httpStatus;

    private InputStream inputStream;

    private HttpHeaders httpHeaders;

    private int rawStatusCode;

    @Override
    public HttpStatus getStatusCode()  {
        return this.httpStatus;
    }

    @Override
    public int getRawStatusCode()  {
        return 0;
    }

    @Override
    public String getStatusText()  {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public InputStream getBody()  {
        return this.inputStream;
    }

    @Override
    public HttpHeaders getHeaders() {
        return this.httpHeaders;
    }
}