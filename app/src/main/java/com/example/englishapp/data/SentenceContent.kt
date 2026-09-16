package com.example.englishapp.data

enum class SentenceType(val title: String, val hindiTitle: String, val punctuation: String) {
    DECLARATIVE("Declarative", "विधानवाचक (Statement)", "."),
    INTERROGATIVE("Interrogative", "प्रश्नवाचक (Question)", "?"),
    IMPERATIVE("Imperative", "आदेश/अनुरोध (Command/Request)", ". / !"),
    EXCLAMATORY("Exclamatory", "विस्मयादिबोधक (Strong Emotion)", "!")
}

data class SentenceExample(
    val english: String,
    val translation: String,
    val note: String = ""
)

data class SentenceTypeDetail(
    val type: SentenceType,
    val descriptionEn: String,
    val descriptionHinglish: String,
    val ruleEn: String,
    val ruleHinglish: String,
    val examples: List<SentenceExample>
)

data class QuizQuestion(
    val id: Int,
    val sentence: String,
    val correctType: SentenceType,
    val explanationEn: String,
    val explanationHinglish: String
)

object SentenceRepository {

    val sentenceDefinitionEn =
        "A sentence is a group of words that expresses a complete thought. It always begins with a capital letter and ends with a punctuation mark (full stop ., question mark ?, or exclamation mark !)."

    val sentenceDefinitionHinglish =
        "Sentence shabdon ka aisa group hota hai jiska ek poora aur saaf matlab banta hai. Ye humesha Capital letter se start hota hai aur full stop (.), question mark (?), ya exclamation mark (!) par end hota hai."

    val sentenceRuleKeyPointsEn = listOf(
        "Capital Letter: Always starts with a capital letter (e.g., 'T' in 'The cat slept.').",
        "Complete Meaning: It must make sense on its own without missing parts.",
        "Punctuation Mark: Must finish with ., ?, or ! depending on the intention."
    )

    val sentenceRuleKeyPointsHinglish = listOf(
        "Capital Letter: Har sentence hamesha bade akshar (Capital letter) se shuru hota hai.",
        "Complete Thought: Iska matlab adhoora nahi hota, poori baat samajh aati hai.",
        "Punctuation Mark: End me full stop (.), question mark (?), ya exclamation mark (!) lagta hai."
    )

    val sentenceTypes: List<SentenceTypeDetail> = listOf(
        SentenceTypeDetail(
            type = SentenceType.DECLARATIVE,
            descriptionEn = "Tells information, shares facts, or makes statements. This is the most common sentence type.",
            descriptionHinglish = "Ye simple statements hote hain jo koi baat, fact, ya jankari batate hain. Ye sabse zyada use hone wala sentence type hai.",
            ruleEn = "Ends with a period (full stop .)",
            ruleHinglish = "Hamesha full stop (.) par khatam hota hai.",
            examples = listOf(
                SentenceExample(
                    english = "The sun rises in the east.",
                    translation = "Suraj poorav se nikalta hai.",
                    note = "Fact (तथ्य)"
                ),
                SentenceExample(
                    english = "I am learning English everyday.",
                    translation = "Main roz English seekh raha hoon.",
                    note = "Statement (बात)"
                ),
                SentenceExample(
                    english = "Rohan loves playing cricket with his friends.",
                    translation = "Rohan ko doston ke sath cricket khelna pasand hai.",
                    note = "Opinion/Fact"
                )
            )
        ),
        SentenceTypeDetail(
            type = SentenceType.INTERROGATIVE,
            descriptionEn = "Asks a direct question to get information. Often starts with Who, What, Where, When, Why, How, or auxiliary verbs like Is, Are, Do, Can.",
            descriptionHinglish = "Ye sawal (questions) poochhne ke liye use hota hai. Aksar What, Where, How, Do, Did, Can se shuru hota hai.",
            ruleEn = "Always ends with a question mark (?)",
            ruleHinglish = "Hamesha question mark (?) par khatam hota hai.",
            examples = listOf(
                SentenceExample(
                    english = "Where do you live?",
                    translation = "Aap kahan rehte hain?",
                    note = "Direct question"
                ),
                SentenceExample(
                    english = "Did you complete your homework?",
                    translation = "Kya aapne apna homework poora kiya?",
                    note = "Yes/No question"
                ),
                SentenceExample(
                    english = "What time does the train leave?",
                    translation = "Train kitne baje nikalti hai?",
                    note = "Information question"
                )
            )
        ),
        SentenceTypeDetail(
            type = SentenceType.IMPERATIVE,
            descriptionEn = "Gives a command, polite request, invitation, or helpful advice. In many imperative sentences, the subject 'You' is understood.",
            descriptionHinglish = "Iska use aadesh (order), request (kripya bolkar), ya achhi salah (advice) dene ke liye hota hai. Isme aksar 'Please' ya verb direct aati hai.",
            ruleEn = "Ends with a period (.) or sometimes an exclamation mark (!) for urgent orders.",
            ruleHinglish = "Full stop (.) ya urgent order me exclamation mark (!) par end hota hai.",
            examples = listOf(
                SentenceExample(
                    english = "Please open the window.",
                    translation = "Kripya khidki kholein.",
                    note = "Polite Request (अनुरोध)"
                ),
                SentenceExample(
                    english = "Turn off the lights before leaving.",
                    translation = "Jane se pehle batti band kar dein.",
                    note = "Command/Instruction (निर्देश)"
                ),
                SentenceExample(
                    english = "Drive slowly and carefully.",
                    translation = "Gaadi dheere aur savdhani se chalayein.",
                    note = "Advice (सलाह)"
                )
            )
        ),
        SentenceTypeDetail(
            type = SentenceType.EXCLAMATORY,
            descriptionEn = "Expresses strong emotions such as deep joy, shock, excitement, wonder, or pain.",
            descriptionHinglish = "Ye dil ki gehri bhavnayein, jaise hairani (surprise), badi khushi ya aashcharya express karta hai.",
            ruleEn = "Always ends with an exclamation mark (!)",
            ruleHinglish = "Hamesha exclamation mark (!) par khatam hota hai.",
            examples = listOf(
                SentenceExample(
                    english = "What a beautiful rainbow in the sky!",
                    translation = "Aasman me kitna sundar indradhanush hai!",
                    note = "Wonder / Khushi"
                ),
                SentenceExample(
                    english = "Hurrah, we won the cricket match!",
                    translation = "Balle balle, hum cricket match jeet gaye!",
                    note = "Celebration / Excitement"
                ),
                SentenceExample(
                    english = "I am so proud of your hard work!",
                    translation = "Mujhe aapki mehnat par bohot garv hai!",
                    note = "Warm praise / Emotion"
                )
            )
        )
    )

    val practiceQuestions: List<QuizQuestion> = listOf(
        QuizQuestion(
            id = 1,
            sentence = "Where can I find the nearest bus stand?",
            correctType = SentenceType.INTERROGATIVE,
            explanationEn = "This sentence asks for information and ends with a question mark (?). Therefore, it is an Interrogative sentence.",
            explanationHinglish = "Ye sentence ek sawal poochh raha hai aur question mark (?) par khatam ho raha hai. Isliye ye Interrogative sentence hai."
        ),
        QuizQuestion(
            id = 2,
            sentence = "The peacock is the national bird of India.",
            correctType = SentenceType.DECLARATIVE,
            explanationEn = "This sentence states a clear fact and ends with a full stop (.). It is a Declarative sentence (statement).",
            explanationHinglish = "Ye ek factual baat (statement) bata raha hai aur full stop (.) par end ho raha hai. Isliye ye Declarative sentence hai."
        ),
        QuizQuestion(
            id = 3,
            sentence = "Please drink at least eight glasses of water daily.",
            correctType = SentenceType.IMPERATIVE,
            explanationEn = "This sentence gives healthy advice and a polite request ('Please'). Therefore, it is an Imperative sentence.",
            explanationHinglish = "Isme 'Please' bolkar achhi health advice di gayi hai. Order, request ya advice Imperative sentence me aate hain."
        ),
        QuizQuestion(
            id = 4,
            sentence = "What a magnificent victory for our team!",
            correctType = SentenceType.EXCLAMATORY,
            explanationEn = "This sentence expresses strong excitement and pride, ending with an exclamation mark (!). It is an Exclamatory sentence.",
            explanationHinglish = "Ye zabardast khushi aur emotion express kar raha hai aur '!' par khatam ho raha hai. Isliye ye Exclamatory sentence hai."
        ),
        QuizQuestion(
            id = 5,
            sentence = "Do you want tea or coffee with breakfast?",
            correctType = SentenceType.INTERROGATIVE,
            explanationEn = "This sentence asks a choice question directly with 'Do you...?' and ends with a question mark (?).",
            explanationHinglish = "Ye sidha sawal poochh raha hai 'Kya aap chai ya coffee lenge?' aur '?' par end hota hai. Interrogative hai."
        ),
        QuizQuestion(
            id = 6,
            sentence = "Close the main door and lock it securely.",
            correctType = SentenceType.IMPERATIVE,
            explanationEn = "This gives a clear instruction or command without saying the subject 'you'. It is an Imperative sentence.",
            explanationHinglish = "Ye ek direct instruction/order de raha hai darwaza band karne ka. Ye Imperative sentence hai."
        )
    )
}
