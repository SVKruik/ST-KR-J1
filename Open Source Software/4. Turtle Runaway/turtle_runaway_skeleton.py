# This example is not working in Spyder directly (F5 or Run)
# Please type '!python turtle_runaway.py' on IPython console in your Spyder.
import tkinter as tk
import turtle, random, time, math

class RunawayGame:
    def __init__(self, canvas, runner, chaser, catch_radius=50):
        self.canvas = canvas
        self.runner = runner
        self.chaser = chaser
        self.catch_radius2 = catch_radius**2

        # Initialize 'runner' and 'chaser'
        self.runner.shape('turtle')
        self.runner.color('blue')
        self.runner.penup()

        self.chaser.shape('turtle')
        self.chaser.color('red')
        self.chaser.penup()

        # Instantiate another turtle for drawing
        self.drawer = turtle.RawTurtle(canvas)
        self.drawer.hideturtle()
        self.drawer.penup()

        # Timer display turtle
        self.timer_drawer = turtle.RawTurtle(canvas)
        self.timer_drawer.hideturtle()
        self.timer_drawer.penup()

        # Score and round timing
        self.score = 0
        self.last_catch_time = None
        self.init_dist = None
        self.round = 1
        self.max_rounds = 3
        self.game_over = False
        self.difficulty = 1  # scales runner behavior

        # Mines
        self.mines = []
        self.mine_radius = 15

    def is_caught(self):
        p = self.runner.pos()
        q = self.chaser.pos()
        dx, dy = p[0] - q[0], p[1] - q[1]
        return dx**2 + dy**2 < self.catch_radius2

    def start(self, init_dist=400, ai_timer_msec=100):
        # Store settings and set up the first round
        self.init_dist = init_dist
        self.ai_timer_msec = ai_timer_msec
        self.reset_round()
        self.canvas.ontimer(self.step, self.ai_timer_msec)

    def reset_round(self):
        # Reset positions and timer for a new round
        dist = self.init_dist if self.init_dist is not None else 400
        self.runner.setpos((-dist / 2, 0))
        self.runner.setheading(0)
        self.chaser.setpos((+dist / 2, 0))
        self.chaser.setheading(180)
        self.start_time = time.perf_counter()
        # Apply difficulty to runner if supported
        if hasattr(self.runner, 'set_difficulty'):
            self.runner.set_difficulty(self.difficulty)
        # Respawn mines for this round
        self.clear_mines()
        self.spawn_mines(self.round * 3)  # small increase each round

    def clear_mines(self):
        for m in self.mines:
            try:
                m.hideturtle()
                m.clear()
            except Exception:
                pass
        self.mines = []

    def spawn_mines(self, count: int):
        # Randomly place a few mines avoiding initial spawn lines
        # This will avoid spawn killing the player immediately
        for _ in range(count):
            t = turtle.RawTurtle(self.canvas)
            t.hideturtle()
            t.shape('circle')
            t.shapesize(0.6, 0.6)
            t.color('black')
            t.penup()
            # place away from center lanes
            x = random.randint(-300, 300)
            y = random.randint(-250, 250)
            if abs(x) < 100 and abs(y) < 80:
                x += 120 if x >= 0 else -120
                y += 90 if y >= 0 else -90
            t.setpos(x, y)
            t.showturtle()
            self.mines.append(t)

    def compute_bonus(self, elapsed_sec: float) -> int:
        """Time-based bonus: faster catches yield more points.
        The bonus points are required to win the game.
        <=5s: +50, <=8s: +35, <=16s: +15, else: +0
        """
        if elapsed_sec <= 5:
            return 50
        if elapsed_sec <= 8:
            return 35
        if elapsed_sec <= 16:
            return 15
        return 0

    def step(self):
        self.runner.run_ai(self.chaser.pos(), self.chaser.heading())
        self.chaser.run_ai(self.runner.pos(), self.runner.heading())

        # Update elapsed time (top-right)
        elapsed = time.perf_counter() - self.start_time
        minutes = int(elapsed // 60)
        seconds = elapsed % 60
        self.timer_drawer.clear()
        self.timer_drawer.penup()
        self.timer_drawer.setpos(250, 300)
        self.timer_drawer.write(f'Time: {minutes:02d}:{seconds:05.2f}')

        # Timeout loss
        if elapsed >= 30:
            # Round lost due to timeout
            self.round += 1
            self.difficulty += 1
            if self.round > self.max_rounds:
                self.game_over = True
            else:
                self.reset_round()

        # Catch detection and scoring (round win)
        if not self.game_over and self.is_caught():
            self.last_catch_time = elapsed
            base = 50
            bonus = self.compute_bonus(elapsed)
            self.score += base + bonus
            # Next round
            self.round += 1
            self.difficulty += 1
            if self.round > self.max_rounds:
                self.game_over = True
            else:
                self.reset_round()

        # Mine collision loss (player hits a mine)
        if not self.game_over and self.mines:
            cx, cy = self.chaser.pos()
            for m in self.mines:
                mx, my = m.pos()
                dx = cx - mx
                dy = cy - my
                if dx*dx + dy*dy <= (self.mine_radius ** 2):
                    # Lose round
                    self.round += 1
                    self.difficulty += 1
                    if self.round > self.max_rounds:
                        self.game_over = True
                    else:
                        self.reset_round()
                    break

        # Update scoreboard (top-left)
        self.drawer.clear()
        self.drawer.penup()
        self.drawer.setpos(-330, 300)
        if self.game_over:
            result = 'WIN' if self.score >= 200 else 'LOSE'
            self.drawer.write(f'Score: {self.score}  Rounds: {self.max_rounds}/{self.max_rounds}  {result}')
        else:
            self.drawer.write(f'Score: {self.score}  Round: {self.round}/{self.max_rounds}')

        # Note) The following line should be the last of this function to keep the game playing
        if not self.game_over:
            self.canvas.ontimer(self.step, self.ai_timer_msec)

class ManualMover(turtle.RawTurtle):
    def __init__(self, canvas, step_move=10, step_turn=10):
        super().__init__(canvas)
        self.step_move = step_move
        self.step_turn = step_turn

        # Register event handlers
        canvas.onkeypress(lambda: self.forward(self.step_move), 'Up')
        canvas.onkeypress(lambda: self.backward(self.step_move), 'Down')
        canvas.onkeypress(lambda: self.left(self.step_turn), 'Left')
        canvas.onkeypress(lambda: self.right(self.step_turn), 'Right')
        canvas.listen()

    def run_ai(self, opp_pos, opp_heading):
        pass

class RandomMover(turtle.RawTurtle):
    """A very simple runner that moves randomly.
    Legacy code kept for reference.
    """
    def __init__(self, canvas, step_move=10, step_turn=10):
        super().__init__(canvas)
        self.step_move = step_move
        self.step_turn = step_turn

    def run_ai(self, opp_pos, opp_heading):
        mode = random.randint(0, 2)
        if mode == 0:
            self.forward(self.step_move)
        elif mode == 1:
            self.left(self.step_turn)
        elif mode == 2:
            self.right(self.step_turn)

class SmartRunner(turtle.RawTurtle):
    """A slightly smarter runner that tends to move away from the chaser.
    Difficulty increases speed and turning responsiveness.
    Kept intentionally simple to preserve skeleton feel.
    """
    def __init__(self, canvas, step_move=12, step_turn=12):
        super().__init__(canvas)
        self.step_move = step_move
        self.step_turn = step_turn
        self._difficulty = 1
        # Keep inside a roughly 700x700 canvas centered at (0,0)
        self._bounds = 330  # half-width/height minus a margin
        self._margin = 20

    def set_difficulty(self, d: int):
        self._difficulty = max(1, d)

    def run_ai(self, opp_pos, opp_heading):
        # Vector distance from chaser to runner
        rx, ry = self.pos()
        ox, oy = opp_pos
        dx, dy = rx - ox, ry - oy
        # Desired heading: away from chaser
        away_heading = (math.degrees(math.atan2(dy, dx)) + 360) % 360

        # If near the edge, bias heading back towards center to stay in bounds
        near_edge = abs(rx) > (self._bounds - self._margin) or abs(ry) > (self._bounds - self._margin)
        if near_edge:
            target_heading = (math.degrees(math.atan2(-ry, -rx)) + 360) % 360
        else:
            target_heading = away_heading
        # Turn towards target heading
        turn = self.step_turn + (self._difficulty - 1) * 2
        cur = self.heading()
        delta = (target_heading - cur + 540) % 360 - 180  # shortest signed diff
        if delta > 0:
            self.left(min(turn, delta))
        else:
            self.right(min(turn, -delta))
        # Move, with small random jitter that increases a bit with difficulty
        jitter = random.randint(-1, 1)
        speed = self.step_move + (self._difficulty - 1) * 2 + max(0, jitter)
        self.forward(speed)

        # Clamp inside bounds if we overshoot (go out of bounds)
        x, y = self.pos()
        clamped_x = max(-self._bounds, min(self._bounds, x))
        clamped_y = max(-self._bounds, min(self._bounds, y))
        if clamped_x != x or clamped_y != y:
            self.setpos(clamped_x, clamped_y)

if __name__ == '__main__':
    # Use 'TurtleScreen' instead of 'Screen' to prevent an exception from the singleton 'Screen'
    root = tk.Tk()
    canvas = tk.Canvas(root, width=700, height=700)
    canvas.pack()
    screen = turtle.TurtleScreen(canvas)

    # TODO) Change the follows to your turtle if necessary
    runner = SmartRunner(screen)
    chaser = ManualMover(screen)

    game = RunawayGame(screen, runner, chaser)
    game.start()
    screen.mainloop()
