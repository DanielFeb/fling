package indi.daniel.fling.r.rlang;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@Getter
public class RLangTryErrorException extends RLangException {

    private REXP error;

    public RLangTryErrorException(REXP error) {
        assert error.inherits("try-error");
        this.error = error;
    }

    public String getErrorMessage() {

        try {
            return error.asString();
        } catch (REXPMismatchException e) {
            StringWriter sw = new StringWriter();
            PrintWriter ps = new PrintWriter(sw);
            e.printStackTrace(ps);
            log.warn(sw.toString());
            throw new RuntimeException(e);
        }
    }

}
