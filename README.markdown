# CSC 335: Noughts & Crosses

![screenshot](https://raw.githubusercontent.com/cjlarose/noughts-and-crosses/master/screenshot.png)

Grader: Rohit Umashankar (rohitu)

## Authors: 

* Reid Knight (knightr)
* Chris La Rose (cjlarose)
* Tanner Prynn (tpyrnn)

## Compile

```bash
mkdir bin
javac -d bin src/controller/*.java src/model/*.java src/view/*.java
```

## Usage

To play a game in the terminal,

```bash
$ java -cp bin view/RunGame
```

To play a tournament against two AI players,

```bash
$ java -cp bin model/RunTournament

Result of playing 1000 games when beginner goes first:
    Beginner: 106
        Ties: 334
Intermediate: 560

Result of playing 1000 games when intermediate goes first:
    Beginner: 28
        Ties: 121
Intermediate: 851
```

Finally, to play a GUI-based game,

```bash
$ java -cp bin controller/Controller
```
