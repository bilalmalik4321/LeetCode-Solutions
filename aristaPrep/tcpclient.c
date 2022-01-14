#include <sys/socket.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <stdarg.h>
#include <errno.h>
#include <fcntl.h>
#include <sys/time.h>
#include <sys/ioctl.h>
#include <netdb.h>

#define SERVER_PORT 80

#define MAXLINE 4096
#define SA struct sockaddr

void err_n_die(const char *fmt, ...);

//program runs and takes an IP address, this IP address is address of the server on the internet
int main(int argc, char **argv){
    int sockfd, n;
    int sendbytes;
    struct sockaddr_in servaddr;
    char sendline[MAXLINE];
    char recvline[MAXLINE];

    if(argc != 2)
        err_n_die("usage: %s <server address>",argv[0]);

    //create a new socket, AF_INET is an internet socket (Address family, internet), 
    //two types of sockets we are using a stream socket (reliability handled by TCP/IP rather then app)
    // 0 equates to use TCP protocol, use last arg to chose protocol
    if ( (sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0 )
        err_n_die("Error while creating the socket!");

    //zero out the address
    bzero(&servaddr, sizeof(servaddr));
    //specify the address family (internet address)
    servaddr.sin_family = AF_INET;
    //specify port, host to network, short, converts number from host byte order (big endian or little endian) to network byte order standard for all
    servaddr.sin_port = htons(SERVER_PORT);

    //convert the string representation of the IP address to a binary representation
    if(inet_pton(AF_INET, argv[1], &servaddr.sin_addr) <= 0)
        err_n_die("inet_pton error for %s ",argv[1]);

    //connect to the address now that it is setup
    if (connect(sockfd, (SA *) &servaddr, sizeof(servaddr)) < 0)
        err_n_die("connect failed!");

    //We're connected, prepare message.
    //create line your going to send, this is a simple send
    //GET (give me a page) / "at the route homepage"  HTTP/1.1 (im using HTTP version 1.1) (the \n \r says end of my request)
    sprintf(sendline, "GET / HTTP/1.1\r\n\r\n");
    sendbytes = strlen(sendline);

    //write request into socket
    if (write(sockfd, sendline, sendbytes) != sendbytes)
        err_n_die("write error");

    //zero outresponse
    memset(recvline, 0, MAXLINE);
    //Now read the server's response, from socket
    while( (n = read(sockfd, recvline, MAXLINE-1)) > 0){
        printf("%s",recvline);
    }
    //if read returns value less then equal to 0, while breaks, handle error
    if (n < 0)
        err_n_die("read error");

    exit(0);//end successfully
}

//error function
void err_n_die(const char *fmt, ...){
    int errno_save;
    va_list ap;

    errno_save = errno;

    va_start(ap, fmt);
    vfprintf(stdout, fmt, ap);
    fprintf(stdout, "\n");
    fflush(stdout);

    if(errno_save != 0){
        fprintf(stdout, "(errno = %d) : %s\n", errno_save, strerror(errno_save));
        fprintf(stdout, "\n");
        fflush(stdout);
    }

    va_end(ap);

    exit(1);
}
