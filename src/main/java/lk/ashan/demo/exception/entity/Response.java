package lk.ashan.demo.exception.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private  int statuscode;
    private String message;

}
