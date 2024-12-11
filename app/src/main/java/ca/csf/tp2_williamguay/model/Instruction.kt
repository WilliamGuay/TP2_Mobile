package ca.csf.tp2_williamguay.model

import org.json.JSONObject

class Instruction(
    val step: String
) {
    companion object{
        fun fromJson(json: String): Instruction{
            val instructionJson = JSONObject(json)
            val instruction = instructionJson.getString("step")

            return Instruction(instruction)
        }
    }
}