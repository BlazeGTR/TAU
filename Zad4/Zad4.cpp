#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <time.h>
#include <cmath>

const bool DEBUG = true;
const int ILOSC_PRZESZKOD = 3;
const int BOARD_SIZE = 5;
char BOARD[BOARD_SIZE][BOARD_SIZE];

int parentX[BOARD_SIZE][BOARD_SIZE];
int parentY[BOARD_SIZE][BOARD_SIZE];
int playerX, playerY;
int endX, endY;

void init_board();
void draw_board();
void move_player();
bool find_and_draw_path();

int main()
{
    srand(time(NULL));
    do
    {
        init_board();
    }
    while(!find_and_draw_path());

    system("cls");
    draw_board();
    while (true)
    {
        move_player();

        if(DEBUG)
        {
            printf("%i %i\n", playerX, playerY);
            for(int y = 0; y < BOARD_SIZE; y++)
            {
                for(int x = 0; x < BOARD_SIZE; x++)
                {
                    if(BOARD[y][x] == 'X')
                    {
                        printf("X pos = %i %i\n", x, y);
                    }
                }
            }
        }

        if (playerX == endX && playerY == endY)
        {
            printf("\nwygrana\n");
            getch();
            break;
        }
        if (BOARD[playerY][playerX] == 'X')
        {
            printf("\nprzegrana\n");
            getch();
            break;
        }
    }
    return 0;
}

void init_board()
{
    system("cls");

    for (int y = 0; y < BOARD_SIZE; y++)
        for (int x = 0; x < BOARD_SIZE; x++)
            BOARD[y][x] = ' ';

    playerX = rand() % BOARD_SIZE;
    playerY = rand() % BOARD_SIZE;
    if(rand()%2)
    {
        playerX = (rand() % 2) == 0 ? 0 : BOARD_SIZE-1;
    }
    else
    {
        playerY = (rand() % 2) == 0 ? 0 : BOARD_SIZE-1;
    }

    do
    {
        endX = rand() % BOARD_SIZE;
        endY = rand() % BOARD_SIZE;
        if(rand()%2)
        {
            endX = (rand() % 2) == 0 ? 0 : BOARD_SIZE-1;
        }
        else
        {
            endY = (rand() % 2) == 0 ? 0 : BOARD_SIZE-1;
        }
    }
    while (
        abs(endX - playerX) <= 1 &&
        abs(endY - playerY) <= 1
    );

    BOARD[playerY][playerX] = 'A';
    BOARD[endY][endX] = 'B';

    for(int i = 0; i < ILOSC_PRZESZKOD; i++)
    {
        int ox, oy;
        do
        {
            ox = rand() % BOARD_SIZE;
            oy = rand() % BOARD_SIZE;
        }
        while(BOARD[oy][ox] != ' ');
        BOARD[oy][ox] = 'X';
    }

    draw_board();
}

void draw_board()
{
    for (int y = 0; y < BOARD_SIZE; y++)
    {
        for (int x = 0; x < BOARD_SIZE; x++)
            printf("+--");
        printf("+\n");

        for (int x = 0; x < BOARD_SIZE; x++)
            printf("| %c", BOARD[y][x]);
        printf("|\n");
    }

    for (int x = 0; x < BOARD_SIZE; x++)
        printf("+--");
    printf("+\n");
}

void move_player()
{
    int key = getch();

    if (key == 224)
    {
        int newX = playerX;
        int newY = playerY;

        key = getch();
        switch (key)
        {
            case 72: if (newY > 0) newY--; break;
            case 80: if (newY < BOARD_SIZE - 1) newY++; break;
            case 75: if (newX > 0) newX--; break;
            case 77: if (newX < BOARD_SIZE - 1) newX++; break;
        }

        if (BOARD[newY][newX] == 'X')
        {
            playerX = newX;
            playerY = newY;
            return;
        }

        BOARD[playerY][playerX] = ' ';
        playerX = newX;
        playerY = newY;
        BOARD[playerY][playerX] = 'A';

        system("cls");
        draw_board();
    }
}

//Sprawdzamy czy jest jakaœ trasa wgl
bool find_and_draw_path()
{
    bool visited[BOARD_SIZE][BOARD_SIZE] = { false };

    int queueX[BOARD_SIZE * BOARD_SIZE];
    int queueY[BOARD_SIZE * BOARD_SIZE];
    int head = 0, tail = 0;

    queueX[tail] = playerX;
    queueY[tail] = playerY;
    tail++;

    visited[playerY][playerX] = true;
    parentX[playerY][playerX] = -1;
    parentY[playerY][playerX] = -1;

    int dx[4] = { 1, -1, 0, 0 };
    int dy[4] = { 0, 0, 1, -1 };

    while (head < tail)
    {
        int x = queueX[head];
        int y = queueY[head];
        head++;

        if (x == endX && y == endY)
            break;

        for (int i = 0; i < 4; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= BOARD_SIZE || ny >= BOARD_SIZE)
                continue;

            if (visited[ny][nx])
                continue;

            if (BOARD[ny][nx] == 'X')
                continue;

            visited[ny][nx] = true;
            parentX[ny][nx] = x;
            parentY[ny][nx] = y;

            queueX[tail] = nx;
            queueY[tail] = ny;
            tail++;
        }
    }

    if (!visited[endY][endX])
        return false;

    int cx = endX;
    int cy = endY;

    while (!(cx == playerX && cy == playerY))
    {
        int px = parentX[cy][cx];
        int py = parentY[cy][cx];

        if (BOARD[cy][cx] == ' ')
            BOARD[cy][cx] = '.';

        cx = px;
        cy = py;
    }

    return true;
}


