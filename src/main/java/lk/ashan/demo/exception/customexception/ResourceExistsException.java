package lk.ashan.demo.exception.customexception;

public class ResourceExistsException extends RuntimeException{

    public ResourceExistsException(){}
    public ResourceExistsException(String message){super(message);}
}
