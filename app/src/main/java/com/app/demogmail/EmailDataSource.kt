package com.app.demogmail

class EmailDataSource {
    private val emailData = mutableListOf(
        Email("Edurila.com", "Subject 1", "Preview of the email content", "12:34 PM"),
        Email("Chris Abad", "Subject 2", "Another preview text here", "11:22 AM"),
        Email("Edurila.com", "Subject 1", "Preview of the email content", "12:34 PM"),
        Email("Chris Abad", "Subject 2", "Another preview text here", "11:22 AM"),
        Email("Edurila.com", "Subject 1", "Preview of the email content", "12:34 PM"),
        Email("Chris Abad", "Subject 2", "Another preview text here", "11:22 AM"),
        Email("Edurila.com", "Subject 1", "Preview of the email content", "12:34 PM"),
        Email("Chris Abad", "Subject 2", "Another preview text here", "11:22 AM"),
        Email("Edurila.com", "Subject 1", "Preview of the email content", "12:34 PM"),
        Email("Chris Abad", "Subject 2", "Another preview text here", "11:22 AM"),
    )

    fun getEmailData(): List<Email> {
        return emailData
    }


}