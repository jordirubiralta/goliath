package com.jrubiralta.goliath.domain.constants;

class Constants {

    companion object {
        const val EMPTY_STRING: String = ""
        const val DEFAULT_LONG: Long = 0
        const val DEFAULT_INT: Int = 0
        const val DEFAULT_DOUBLE: Double = 0.0
        const val DEFAULT_FLOAT: Float = 0F
        const val TRUE_BOOLEAN_IN_STRING = "1"
        const val LOGIN_PATH: String = "Login"
        const val REGISTER_PATH: String = "Register"
        const val PASSWORD_RECOVERY_PATH: String = "ResetPassword"
        const val DEFAULT_BOOLEAN: Boolean = false
        const val TEXT_PLAIN: String = "text/plain"
        const val AGENDA_LINK_ID: String = "{813B1CAA-5BCF-4317-B60C-7F56F824F3F6}"
        const val TERMS_AND_CONDITIONS_LINK_ID: String = "{C2FBBB87-540F-4F76-99B9-D79613936330}"
        const val HTML_LINK_TEMPLATE: String = "<a href='%s'>%s</a>"
        const val DEFAULT_RETROFIT_TIMEOUT_SECONDS: Long = 30
        const val CURRENT_USER_ID = "1"
        const val BASIC_RESPONSE_OK = "OK"
        const val BASIC_RESPONSE_KO = "KO"
        const val SMS_VALIDATE_CODE_LENGTH: Int = 6
        const val KEY_REALM_KEY: String = "KEY_REALM_KEY"

        fun preferencesName(buildType: BuildType): String = when (buildType) {
            BuildType.DEBUG -> "goliath_debug"
            BuildType.RELEASE -> "goliath"
        }
    }
}

fun buildType(type: String): BuildType = when (type) {
    "debug" -> BuildType.DEBUG
    else -> BuildType.RELEASE
}

enum class BuildType {
    DEBUG, RELEASE
}
