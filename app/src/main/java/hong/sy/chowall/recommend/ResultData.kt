package hong.sy.chowall.recommend

data class ResultData(
    val img : Int = -1,
    val name : String,
    val address : String = "",
    val phone : String = "",
    val time : String = "",
    var breakTime : String = ""
)
