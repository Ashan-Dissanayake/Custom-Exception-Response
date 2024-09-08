package lk.ashan.demo.exception.customexception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){}
    public ResourceNotFoundException(String message){super(message);}
}
