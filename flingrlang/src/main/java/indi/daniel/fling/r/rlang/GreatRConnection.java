package indi.daniel.fling.r.rlang;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import org.apache.commons.text.StringEscapeUtils;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;

@AllArgsConstructor
public class GreatRConnection {
    @Delegate
    private RConnection rc;
    public REXP tryParseAndEval(String cmd) throws REngineException, REXPMismatchException, RLangTryErrorException {

        String escapedCMD = StringEscapeUtils.escapeJava(cmd);
        String finalCmd = "try( {\n" +
                "    expr = parse(text=\"" + escapedCMD + "\")\n" +
                "    eval(expr)\n" +
                "},\n" + "silent=TRUE)";
//                "warning = function(w) { cat(\"warning\", conditionMessage(w), \"\\n\\n\")},\n" +
//                "error = function(e) { cat(\"error\", conditionMessage(e), \"\\n\\n\")})";
        REXP r = rc.parseAndEval(finalCmd);
        if (r.inherits("try-error")) {
            throw new RLangTryErrorException(r);
        } else {
            return r;
        }
    }

    public REXP tryParseAndEval2(String cmd) throws REngineException, REXPMismatchException, RLangTryErrorException {

        String escapedCMD = StringEscapeUtils.escapeJava(cmd);
        String finalCmd = "tryCatch( {\n" +
                "    expr = parse(text=\"" + escapedCMD + "\")\n" +
                "    eval(expr)\n" +
                "},\n" +
                "warning = function(w) { cat(\"warning\", conditionMessage(w), \"\\n\\n\")},\n" +
                "error = function(e) { cat(\"error\", conditionMessage(e), \"\\n\\n\")})";
        REXP r = rc.parseAndEval(finalCmd);
        if (r.inherits("try-error")) {
            throw new RLangTryErrorException(r);
        } else {
            return r;
        }
    }

}
