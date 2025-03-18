package com.llinsoft.finalchat.domain.request_making


enum class ApiProvider {
    OPENAI,
    //DEEPSEEK,
    //GEMINI
}

enum class OpenAiModel(
    val label: String,
    val id: String
) {
    GPT4("gpt 4", "gpt-4"),
    GPTO1("gpt o1", "gpt-o1")
}

enum class OpenAiTemperatureSettings(
    val temperature: Double,
    val topP: Double,
    val explanation: String
) {
    MIN(
        temperature = 0.2,
        topP = 0.1,
        explanation = "Very focused"
    ),
    LOW(
        temperature = 0.3,
        topP = 0.2,
        explanation = "Focused"
    ),
    MED(
        temperature = 0.5,
        topP = 0.5,
        explanation = "Medium"
    ),
    HI(
        temperature = 0.6,
        topP = 0.7,
        explanation = "Creative"
    ),
    TOP(
        temperature = 0.7,
        topP = 0.8,
        explanation = "Very creative"
    )
}