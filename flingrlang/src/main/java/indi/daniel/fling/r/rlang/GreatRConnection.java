package indi.daniel.fling.r.rlang;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;

@AllArgsConstructor
public class GreatRConnection {
    @Delegate
    private RConnection rc;
    public REXP tryParseAndEval(String cmd) throws REngineException, REXPMismatchException, RLangTryErrorException {

        String functionName = "simfunc";
        String finalCmd = "\n" +
                "try( { \n" +
                functionName + " <- function() {\n" +
                "\n" +
                cmd +
                "\n" +
                "}\n" +
                functionName +"()\n" +
                "},silent=TRUE)";
        REXP r = rc.parseAndEval(finalCmd);
        if (r.inherits("try-error")) {
            throw new RLangTryErrorException(r);
        }
        else {
            return r;
        }
    }

}
