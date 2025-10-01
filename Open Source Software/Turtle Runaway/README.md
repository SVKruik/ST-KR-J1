# Turtle Runaway - Stefan Kruik

You play as the catcher, and try to catch the AI fleeing turtle.

I completed the assignment by adding a timer, smarter turtle, deadly obstacles and the scoring system.

#### Score System

- When you catch the turtle you get 50 points.
- Depending on how fast you caught the turtle, you get additional bonus points.
  - Less or equal than 5 seconds: +50 extra points (so 100 total)
  - Less or equal than 8 seconds: +35 extra points (85 total)
  - Less or equal than 16 seconds: +15 extra points (65 total)
  - If you take longer, you will not get any points.
- If you take longer than 30 seconds you will lose the round.

#### Round Progression

- After each succesfull the game gets harder.
  - The AI will become faster and will dodge more.
  - More mines will spawn. If you touch on of the mines, you will lose the round.
    - The running turtle can go through the mines unharmed, so be careful if you follow their path!

#### Win Conditions

If you have 200 points or more after the 3 games you win, otherwise you lose.

Because the rounds themselves only award 50 points, you need the bonus points in order to win. So be quick!
