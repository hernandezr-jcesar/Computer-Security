#include <arpa/inet.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <stddef.h>
#include <ctype.h>

#define MAXIMA_LONGITUD_CADENA 100
#define MAXSIZE 1024

char *caesarcipher(char* src){
	if(src == NULL){
      return NULL;
    }
  
    char* result = malloc(strlen(src));
    
    if(result != NULL){
      strcpy(result, src);
      char* current_char = result;
      
      while(*current_char != '\0'){
        //Only increment alphabet characters
        if((*current_char >= 97 && *current_char <= 122) || (*current_char >= 65 && *current_char <= 90)){
          if(*current_char > 109 || (*current_char > 77 && *current_char < 91)){
            //Characters that wrap around to the start of the alphabet
            *current_char -= 3;
          }else{
            //Characters that can be safely incremented
            *current_char += 3;
          }
        }
        current_char++;
      }
    }
    return result;
}
char *rot13(const char *src)
{
    if(src == NULL){
      return NULL;
    }
  
    char* result = malloc(strlen(src));
    
    if(result != NULL){
      strcpy(result, src);
      char* current_char = result;
      
      while(*current_char != '\0'){
        //Only increment alphabet characters
        if((*current_char >= 97 && *current_char <= 122) || (*current_char >= 65 && *current_char <= 90)){
          if(*current_char > 109 || (*current_char > 77 && *current_char < 91)){
            //Characters that wrap around to the start of the alphabet
            *current_char -= 13;
          }else{
            //Characters that can be safely incremented
            *current_char += 13;
          }
        }
        current_char++;
      }
    }
    return result;
}

int main(int argc, char const *argv[])
{
	if (argc =! 3)
	{
		printf("Uso: cliente [DIRECCION IP] [PUERTO]\n");		
		exit(-1);
	}	
	while(1){		
		
		int sockfd = socket(AF_INET, SOCK_STREAM, 0);

		if(sockfd<0){
			printf("Socket erroneo\n");
			exit(-1);
		}
		if(setsockopt(sockfd, SOL_SOCKET, SO_REUSEADDR, &(int){1},sizeof(int))<0){
		perror("setsockopt(SO_REUSADDR failed");
		}

		struct sockaddr_in servidor;
		servidor.sin_addr.s_addr = inet_addr(argv[1]);
		servidor.sin_port = atoi(argv[2]);
		servidor.sin_family = AF_INET;

		int codigo_connect = connect(sockfd, (struct sockaddr *)&servidor, sizeof(servidor));

		if(codigo_connect < 0){
			printf("ERROR: connect no exitoso\n");
			exit(-1);
		}else{
			printf("Conexion establecida correctamente...\n");
		}		

		char buffer[MAXIMA_LONGITUD_CADENA];
		char msgtoserver[MAXIMA_LONGITUD_CADENA];
		char msgfromserver[MAXIMA_LONGITUD_CADENA];	
		
		printf("Mensaje para Servidor:\n");
		fgets(msgtoserver, MAXIMA_LONGITUD_CADENA, stdin);
		strtok(msgtoserver, "\n");		
		//Comienza fase de comunicacion		

		int num_bytes_r = recv(sockfd, msgfromserver, sizeof(msgfromserver),0);		
			if(num_bytes_r < 0){
				//printf("ERROR: recv no exitoso\n");
				//exit(-1);
				printf("Mensaje para Servidor:\n");
				fgets(msgtoserver, MAXIMA_LONGITUD_CADENA, stdin);
				strtok(msgtoserver, "\n");
				
				char ch;
				int i;
				int key = 3;
				for(i = 0; msgtoserver[i] != '\0'; ++i){
				ch = msgtoserver[i];
				if(ch >= 'a' && ch <= 'z'){
				ch = ch + key;
				if(ch > 'z'){
				ch = ch - 'z' + 'a' - 1;
				}
				msgtoserver[i] = ch;
				}
				else if(ch >= 'A' && ch <= 'Z'){
				ch = ch + key;
				if(ch > 'Z'){
				ch = ch - 'Z' + 'A' - 1;
				}
				msgtoserver[i] = ch;
				}
				}
				
				printf("Mensaje cifrado con cesar: %s\n",msgtoserver);

				int num_bytes = send(sockfd, msgtoserver, strlen(msgtoserver),0);
				if(num_bytes<0){
					printf("ERROR: send no exitoso\n");
					exit(-1);
				}

		}

		msgfromserver[num_bytes_r] =  '\0';
		printf("Mensaje del servidor: %s\n", msgfromserver);	

		char descifradorot[MAXIMA_LONGITUD_CADENA];		
		strcpy(descifradorot,rot13(msgfromserver));
		printf("Mensaje descifrado: %s\n",descifradorot);										
				
	}
	

	return 0;
}