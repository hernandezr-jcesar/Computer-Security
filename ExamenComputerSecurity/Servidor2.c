#include <arpa/inet.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#include <stdlib.h>
#include <stddef.h>

#define MAXIMA_LONGITUD_CADENA 100
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
	char msgtoclient[MAXIMA_LONGITUD_CADENA];
	char msgfromclient[MAXIMA_LONGITUD_CADENA];	

	int sockfd = socket(AF_INET, SOCK_STREAM, 0);

	if(sockfd<0){
		printf("Error opening socket\n");
		exit(-1);
	}
	if(setsockopt(sockfd, SOL_SOCKET, SO_REUSEADDR, &(int){1},sizeof(int))<0){
		perror("setsockopt(SO_REUSADDR failed");
	}
	
	struct sockaddr_in servidor, cliente;
	servidor.sin_addr.s_addr = inet_addr(argv[1]);
	servidor.sin_port = atoi(argv[2]);
	servidor.sin_family = AF_INET;	
	int codigo_bind = bind(sockfd, (struct sockaddr *)&servidor, sizeof(servidor));

	if(codigo_bind < 0){
		printf("ERROR: bind no exitoso\n");
		exit(-1);
	}

	if(listen(sockfd,4)<0){
		printf("Error: listen no exitoso\n");
		exit(-1);
	}
	while(1){		
	
		
		printf("Esperando cliente...\n");		
		//lo que devuelve accept es el identificador del canal del comunicacion credo con el cliente		
		int size = sizeof(cliente);
		int c_accept = accept(sockfd, (struct sockaddr *)&cliente,&size);
		if(c_accept < 0){
			printf("ERROR: accept no exitoso\n");
			exit(-1);
		}else{
			printf("Cliente conectado\n");		
		}
		printf("Mensaje para Cliente:\n");		
		fgets(msgtoclient, MAXIMA_LONGITUD_CADENA, stdin);
		strtok(msgtoclient, "\n");
		
		char cifradorot[MAXIMA_LONGITUD_CADENA];		
		strcpy(cifradorot,rot13(msgtoclient));

		//Si no funciona el send tiene que hacer un recive
		int num_bytes = send(c_accept, cifradorot, strlen(cifradorot),0);
		if(num_bytes<0){
			//printf("ERROR: send no exitoso\n");
			//exit(-1);
			//Comienza fase de comunicacion
			int num_bytes_r = recv(c_accept, msgfromclient, sizeof(msgfromclient),0);

			if(num_bytes_r <0){
				printf("ERROR: recv no exitoso\n");
				exit(-1);
			}

			printf("Mensaje del cliente: %s\n", msgfromclient);	
				
			char ch;
			int i, key;
			key =3;
			for(i = 0; msgfromclient[i] != '\0'; ++i){
			ch = msgfromclient[i];
			if(ch >= 'a' && ch <= 'z'){
			ch = ch - key;
			if(ch < 'a'){
			ch = ch + 'z' - 'a' + 1;
			}
			msgfromclient[i] = ch;
			}
			else if(ch >= 'A' && ch <= 'Z'){
			ch = ch - key;
			if(ch < 'A'){
			ch = ch + 'Z' - 'A' + 1;
			}
			msgfromclient[i] = ch;
			}
			}		
			printf("Mensaje descifrado con cesar: %s\n",msgfromclient);

		}		
		
				
		close(c_accept);		
	}
	
	return 0;
}