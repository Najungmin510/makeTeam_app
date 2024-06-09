package com.example.maketeam_app.openai

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL

object GPTURL {
    private fun gptRules(): String {
        return ChatGPTPrompt.SYSTEMROLE
    }

    fun chatGPT(userMessage: String): String {
        val url = "https://api.openai.com/v1/chat/completions"
        val apiKey = "YOUR_API_KEY"
        val model = "gpt-3.5-turbo"
        val systemPrompt = gptRules()
        val temperature = "0.1"

        return try {
            // Create the HTTP POST request
            val obj = URL(url)
            val con = obj.openConnection() as HttpURLConnection
            con.requestMethod = "POST"
            con.setRequestProperty("Authorization", "Bearer $apiKey")
            con.setRequestProperty("Content-Type", "application/json")

            // Build the request body
            val body = """
                {
                    "model": "$model",
                    "messages": [
                        {"role": "system", "content": "$systemPrompt"},
                        {"role": "user", "content": "$userMessage"}
                    ],
                    "temperature":$temperature
                }
                """.trimIndent()
            con.doOutput = true
            val writer = OutputStreamWriter(con.outputStream)
            writer.write(body)
            writer.flush()
            writer.close()

            // Get the response
            val inStream = BufferedReader(InputStreamReader(con.inputStream))
            var inputLine: String?
            val response = StringBuilder()
            while (inStream.readLine().also { inputLine = it } != null) {
                response.append(inputLine)
            }
            inStream.close()

            // returns the extracted contents of the response.
            extractContentFromResponse(response.toString())

        } catch (e: SocketTimeoutException) {
            "${e.toString()} 오류가 발생했습니다."
        } catch (e: IOException) {
            "${e.toString()} 이 오류 문구를 운영자에게 전달해 주세요."
        }
    }

    private fun extractContentFromResponse(response: String): String {
        val startMarker = response.indexOf("content") + 11
        val endMarker = response.indexOf("\"", startMarker)
        return response.substring(startMarker, endMarker)
    }
}

fun main() {
    val userMessage = "안녕하세요ㅕ 저는 송태웅입니다?"
    val response = GPTURL.chatGPT(userMessage)
    println("ChatGPT 응답: $response")
}