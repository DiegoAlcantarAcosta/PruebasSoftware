package casoDeUsoAgregar;

public class CasoDeUsoAgregarException extends Exception{

    public CasoDeUsoAgregarException() {
    }

    public CasoDeUsoAgregarException(String message) {
        super(message);
    }

    public CasoDeUsoAgregarException(String message, Throwable cause) {
        super(message, cause);
    }

    public CasoDeUsoAgregarException(Throwable cause) {
        super(cause);
    }

    public CasoDeUsoAgregarException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
