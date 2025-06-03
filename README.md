# PLChallenge
# Scenario
You’re sipping your coffee and scrolling through your Kanban board when you notice a new ticket.

**Title**: Festive Battery Indicator

**Description**: Missed Valentine’s update. St. Patrick’s is next. Now, it’s both. Battery = heart ♥️ + clover 🍀. Make it festive.

You blink. Well… It’s definitely not the weirdest request you’ve seen. Time to bring some romantic luck to this app!

# Feature Goal
* Create a UI component that indicates the device's battery level with a unique animation for when the battery level is low and one for when the battery is sufficiently charged.

# Requirements
* Show a heart ♥️ and clover 🍀 icon at both ends of the battery level indicator
* You can find the mockup and animation in the attachments section 👉🏼
* Observe the device's battery level and feed the information to the UI component
* The icons change based on the battery level percentage %
  * ≤20%
     * Heart starts pulsing on repeat continuously
     * As the heart scales up, it gets more tinted with a white color
     * Clover remains greyed out and scaled-down
  * ≥80%
     * The clover scales up slightly
     * The heart remains greyed out and scaled down
  * ≥20% and <80%
     * Both icons are greyed and scaled-down as per the mockups
* The bar fills using an animation so that the bar appears to extend or shrink as the battery level changes
* The bar color animates between these colours, each fully matching its color at the target % value
  * 20% - ![#FF4E51](https://placehold.co/15x15/FF4E51/FF4E51.png) Red - #FF4E51
  * 50% - ![#FCB966](https://placehold.co/15x15/FCB966/FCB966.png) Yellow - #FCB966
  * 80% - ![#19D181](https://placehold.co/15x15/19D181/19D181.png) Green - #19D181


https://github.com/user-attachments/assets/fa9538a5-8dab-48bd-a0ee-6913fd7b4185

