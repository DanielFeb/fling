

tryCatch( {
    simfunc <- function() {
        print(value)
    }
    simfunc()
},
warning = function(w) { cat("warning", conditionMessage(w), "\n\n")},
error = function(e) { cat("error", conditionMessage(e), "\n\n")},
finally = {
    print("finally")
})




tryCatch( {
    expr = parse(text="print(value)\n {\n{\nprint()\n}\n}")
    eval(expr)
},
warning = function(w) { cat("warning", conditionMessage(w), "\n\n")},
error = function(e) { cat("error", conditionMessage(e), "\n\n")})


tryCatch( {
    expr = parse(text="result=\"\"\nv <- LETTERS[1:4]\nfor ( i in v) {\n   print(i)\n   print(value){\n   result=paste(result,i)\n}\nreturn(result)\n")
    eval(expr)
},
warning = function(w) { cat("warning", conditionMessage(w), "\n\n")},
error = function(e) { cat("error", conditionMessage(e), "\n\n")})