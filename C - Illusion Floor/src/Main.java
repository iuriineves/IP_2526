import java.util.Scanner;

void main() {
    // get user input
    Scanner input = new Scanner(System.in);
    
    int width = input.nextInt();
    int length = input.nextInt();
    int blackTile = input.nextInt();
    int grayTile = input.nextInt();
    input.close();

    /*
    measure how many groups of black + gray tiles fit in both axes and subtract
    black tile side to account for the right and bottom side black tiles
    */
    int tileGroupSize = blackTile + grayTile;
    int unitWidth = (width - blackTile) / tileGroupSize;
    int unitLength = (length - blackTile) / tileGroupSize;

    /*
    calculate black tiles by multiplying amount of "units" on both axis, plus
    the extra line and column, and the bottom right corner
    the white tiles are similar, only without the extra line, column, and corner
    */
    int blackTiles = unitWidth * unitLength + unitLength + unitWidth + 1;
    int whiteTiles = unitWidth * unitLength;

    /*
    calculate gray tiles by calculating how many gray tiles are possible in this kitchen size,
    subtracting the gray tiles equivalent to the black tiles, and subtracting the white tiles
    */
    int possibleGrayTiles = (width / grayTile) * (length / grayTile);
    int grayTilesPerBlackTile = blackTiles * (blackTile / grayTile) * (blackTile / grayTile);
    int grayTiles = possibleGrayTiles - grayTilesPerBlackTile - whiteTiles;

    // present the data to the user
    System.out.println(blackTiles);
    System.out.println(grayTiles);
    System.out.println(whiteTiles);
}