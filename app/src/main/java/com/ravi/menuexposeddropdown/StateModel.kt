package com.ravi.menuexposeddropdown

import com.google.gson.annotations.SerializedName

data class StateModel(
    @SerializedName("states")
    val states: List<State>
) {

}

//Add to String
data class State(
    @SerializedName("districts")
    val districts: List<String>,
    @SerializedName("state")
    val state: String

) {
    override fun toString(): String {
        return state
    }
}

