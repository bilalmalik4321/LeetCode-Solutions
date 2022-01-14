#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <string.h>
#include <netdb.h>
#include <netinet/in.h>
#define MAX 80
#define PORT 8080
#define SA struct sockaddr

void func(int connfd){
	char buff[MAX];
	int n;

	for(;;){
		
		bzero(&buff,  sizeof(buff));
		read(connfd, &buff, sizeof(buff));
		printf("Client says : %s", buff);
	
		bzero(&buff, sizeof(buff));
		printf("What to respond with? : ");
		while( (buff[n++] = getchar()) == '\n' )
			;

		write(connfd, &buff, sizoeof(buff));

		if(strncmp("exit",buff,4) == 0){
			printf("Server is exitting");
			break;
		}

	}

}


int main(){
	int sockfd, connfd, len;
	struct sockaddr_in servaddr, cli;

	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	
	if(sockfd < 0){
		printf("Unable to create socket...\n");
		exit(0);
	}
	else
		printf("Successfully created socket...\n");
	
	bzero(&servaddr, sizeof(servaddr));
	
	servaddr.sin_family = AF_INET;
	servaddr.sin_port = htons(PORT);
	servaddr.sin_addr.s_addr = htonl(INADDR_ANY);

	if (bind(sockfd, (SA*)&servaddr, sizeof(servaddr)) != 0){
		printf("Unable to bind...\n");
		exit(0);
	}
	else
		printf("Successfully binded.");

	if (listen(sockfd, 5) != 0){
		printf("Unable to listen.");
		exit(0);
	}
	else
		printf("Successfully able to listen...\n");

	if ( (connfd = accept(sockfd, (SA*)&cli, sizeof(cli)) ) < 0){
		printf("unable to accept request...\n");
		exit(0);
	}
	else
		printf("Successfully able to accept request.");

	func(connfd);

	close(connfd);
		
}


