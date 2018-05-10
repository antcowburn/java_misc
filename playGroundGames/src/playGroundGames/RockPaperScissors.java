package playGroundGames;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

	public static void main(String[] args) {
		Random hand = new Random();
		Scanner playHand = new Scanner(System.in);
		Scanner keepPlaying = new Scanner(System.in);
		boolean playing = true;

		while (playing) {
//			Set the computer's hand
			int compHand = hand.nextInt(3);
			
			System.out.println("Choose your hand: r for rock, p for paper, or s for scissors:");
			//set this to catch any input errors
			boolean cont= true;
			do {
				cont= true;
//				Set the player's hand

				String playerHand = playHand.next();
//				Check to see who wins and output the winner
				if (Arrays.asList("r", "p", "s").contains(playerHand)) {
					switch (playerHand) {
	
					case "r":
						switch (compHand) {
						case 0:
							System.out.println("It's a Rock VS Rock smash up!");
							System.out.println("It's a draw!");
							break;
						case 1:
							System.out.println("Your Rock get's smothered by my superior Paper!");
							System.out.println("You lose!");
							break;
						case 2:
							System.out.println(
									"Your Rock just destroyed my Scissors! Hey, are you going to buy me some new ones?!");
							System.out.println("You win!");
							break;
						default:
							System.out.println("Woops, something screwed up! Don't blame me, just try again!");
							break;
						}
						break;
	
					case "p":
						switch (compHand) {
						case 0:
							System.out.println("Your Paper turns my Rock to dust! (Somehow...)");
							System.out.println("You win!");
							break;
						case 1:
							System.out.println("Your Paper and my Paper collide to create a massive nothingness!");
							System.out.println("It's a draw!");
							break;
						case 2:
							System.out.println("Your Paper just got sliced by my Scissors! Hope you like confetti!");
							System.out.println("You lose!");
							break;
						default:
							System.out.println("Woops, something screwed up! Don't blame me, just try again!");
							break;
						}
						break;
	
					case "s":
						switch (compHand) {
						case 0:
							System.out.println("Your Scissors just got crushed by my Rock!");
							System.out.println("You lose!");
							break;
						case 1:
							System.out.println("Your Scissors just cut up my Paper! Luckily tehnology has advanced enough to use this screen. Otherwise I wouldn't have anything to write on!");
							System.out.println("You win!");
							break;
						case 2:
							System.out.println(
									"Your Scissors and my Scissors are scissoring... ummm... I think we should give them some privacy....");
							System.out.println("It's a draw!");
							break;
						default:
							System.out.println("Woops, something screwed up! Don't blame me, just try again!");
							break;
						}
						break;
						
					default:
						System.out.println("Woops, something screwed up! Don't blame me, just try again!");
						break;
					}
	
				}
				else {
					System.out.println("What did you just type?!");
					System.out.println("I said choose your hand: r for rock, p for paper, or s for scissors:");
					cont = false;
				} 
			}while (cont == false);

			System.out.println("Well that was an enjoyable game! Enter y to play again or n to exit.");
			boolean asking = true;
			while (asking == true) {
			String playAgain = keepPlaying.next();
			switch (playAgain) {
			case "y": {
				System.out.println("Let's go again!");
				asking = false;
				break;
			}
			
			case "n": {
				System.out.println("See you next time!");
				playing = false;
				asking = false;
				break;
			}
			
			default: {
				System.out.println("Enter y or n, it's not that hard...");
				break;
			}
			}
			}
		}
	}

}
