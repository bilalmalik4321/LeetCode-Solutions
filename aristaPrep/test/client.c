#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <string.h>
#include <netdb.h>
#define MAX 80
#define PORT 8080
#define SA struct sockaddr

void func(int sockfd)
{
	char buff[MAX];
	int n;

	for (;;)
	{
		bzero(&buff, sizeof(buff));
		printf("What should we say to the server? :");
		n = 0;
		while( (buff[n++] = getchar()) != '\n' )
			;
		
		write(sockfd, &buff, sizeof(buff));
		bzero(&buff, sizeof(buff));
		
		read(sockfd, &buff, sizeof(buff));
		printf("Server says : %s",buff);

		if( strncmp(buff,"exit",4) == 0 ){
			printf("Client exiting...\n");
			break;
		}
		

	}	

}

int main()
{
	int sockfd;
	struct sockaddr_in servaddr;

	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	
	if (sockfd < 0){
		printf("Unable to creeate socket...\n");
		exit(0);
	}
	else
		printf("Able to connect with the socket...\n");

	bzero(&servaddr, sizeof(servaddr));

	servaddr.sin_family = AF_INET;
	servaddr.sin_port = htons(PORT);
	servaddr.sin_addr.s_addr = inet_addr("127.0.0.1");

	if (connect(sockfd,(SA*)&servaddr, sizeof(servaddr)) != 0)
	{
		printf("Unable to connect...\n");
		exit(0);
	}
	else
		printf("Able to connect...\n");

	func(sockfd);

	close(sockfd);

}

