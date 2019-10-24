test <- function () {
    result=""
    v <- LETTERS[1:4]
    for ( i in v) {
       result=paste(result,i)
    }
    return(result)
}


try( {
simfunc <- function() {

print("hello")
}simfunc()
},silent=TRUE)