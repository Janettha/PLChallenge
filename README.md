# PLChallenge
# Scenario
You're working on a rewards-based mobile application encouraging users to complete challenges and earn prizes. You've been tasked with doing R&D (Research & Development) on a new feature request from the client that introduces a celebratory confetti animation when users receive an SMS confirming their reward.

# Feature Goal
* Show confetti animation whenever a user receives an SMS from a mobile number "+4444555551" and it matches this template: "Congratulations! You've earned 500 points.".

# Requirements
* Detect incoming SMS messages
* Filter SMS messages based on sender and specific keywords
* Trigger confetti animation in the app when a valid SMS is received
* The clients have shared a GIF of a basic confetti animation they would like to see replicated as closely as possible. You can find the GIF in the attachments section 👉🏼
    - Each piece of confetti is one of the basic 2D shapes that differ in scale, rotation, and color.
* Edge cases that don't need to be considered at this stage of development
    - SMS received when app is closed
    - Multiple SMS messages received in a short time