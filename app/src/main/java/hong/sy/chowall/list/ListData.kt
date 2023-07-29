package hong.sy.chowall.list

data class ListData(
    val img : Int,
    val name : String,
    val address : String = "",
    val phone : String = "",
    val time : String = "",
    var breakTime : String = ""
)
