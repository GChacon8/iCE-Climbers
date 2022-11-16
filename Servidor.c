/***********************************************************/
/*********************PROGRAMA SERVIDOR*********************/
/***********************************************************/

// Estructura utilizada por el socket
/*
struct sockaddr_in{
	short int sin_family; 			// Familia de direcciones. Usaremos siempre "AF_INET"
	unsigned short int sin_port; 	// Numero de puerto
	struct in_addr sin_addr;		// Estructura que indica la direccion IP
	unsigned char sin_zero[8];		// Array de 8 bytes rellenados a cero.
}
*/


// Protocolos disponibles para el transporte de datos
/*
TCP (SOCK_STREAM) --> 	Socket de flujo que define una comunicacion bidireccional, confiables y orientada a la conexion.
						Todo lo que se mande desde el origen llegará al destino.
						Necesitan mantener una conexion abierta.

UDP (SOCK_DGRAM) --> 	Socket de datagramas que no esta orientado a la conexion y no es confiable.
						No precisan mantener una conexion abierta.
*/

#include <stdio.h>
#include <winsock2.h>
#include <string.h>
#include <stdlib.h>
#include "stdbool.h"
#include "pthread.h"


void concatenarCharACadena(char c, char *cadena)
{
    char cadenaTemporal[2];
    cadenaTemporal[0] = c;
    cadenaTemporal[1] = '\0';
    strcat(cadena, cadenaTemporal);
}

char* menu(char *respuesta){
    char tipo[20];
    char lado[20];
    char piso[20];
    printf("digite\nY para yeti\nA para ave\nH para hielo\nrespuesta: ");
    gets(tipo);
    //printf( strcmp(tipo, "1"));
    if(strcmp(tipo,"Y") == 0){
        printf("lado donde desea que aparezca\nD para derecha\nI para izquierda\nrepuesta: ");
        gets(lado);
        printf("piso donde desea que apareza (digite el numero de piso)\nrespuesta: ");
        gets(piso);
        strcat(respuesta, strcat(tipo,strcat(lado,piso)));
    }
    else if(strcmp(tipo,"A") == 0){
        printf("piso donde desea que apareza (digite el numero de piso)\nrespuesta: ");
        gets(piso);
        return strcat(respuesta,strcat(tipo,piso));
    }
    else if(strcmp(tipo,"H") == 0){
        printf("posicion en X donde desea que aparezca\nrepuesta: ");
        gets(lado);
        printf("piso donde desea que apareza (digite el numero de piso)\nrespuesta: ");
        gets(piso);
        return strcat(respuesta,strcat(tipo,strcat(lado,piso)));
    }
    else{
        printf("\n\nentreda invalida intente de nuevo\n\n");
        return menu(respuesta);
    }


}

void handle_connection(SOCKET skt2){
    char mensaje_entrada[2000];
    char mensaje_salida[200];
    //SOCKET skt2=*p_skt2;
    //free(p_skt2);
    int recv_size;
    printf("Esperando mensaje entrante...\n");
    if((recv_size = recv(skt2, mensaje_entrada, 2000, 0)) == SOCKET_ERROR)
        printf("Recepcion fallida\n");

    mensaje_entrada[recv_size] = '\0';
    printf("%s\n\n", mensaje_entrada);

    //printf("Ingrese respuesta: "); gets(mensaje);
    menu(mensaje_salida);

    if(send(skt2, mensaje_salida, strlen(mensaje_salida), 0) < 0){
        printf("Error al enviar mensaje\n");
        exit(-1);
    }

    printf("Respuesta enviada exitosamente\n");

    //system("pause");
}

int main(int argc, char **argv){
    WSADATA wsa;
    SOCKET skt, skt2;
    int longitud_cliente, puerto = 5000;
    struct sockaddr_in server, cliente;

    /**********************INICIALIZACION WINSOCK**********************/
    printf("Inicializando Winsock...");
    if(WSAStartup(MAKEWORD(2, 2), &wsa) != 0){
        printf("Error al inicializar winsock\n");
        exit(-1);
    }
    printf("Winsock inicializado\n");

    /**********************CREACION SOCKET**********************/
    printf("Creando socket...");
    if((skt = socket(AF_INET, SOCK_STREAM, 0)) == INVALID_SOCKET){
        printf("Error creando socket\n");
        exit(-1);
    }
    printf("Socket creado\n");

    /**********************CONFIGURACION SOCKADDR_IN**********************/
    server.sin_family = AF_INET; // Familia TCP/IP
    server.sin_port = htons(puerto); // Puerto
    server.sin_addr.s_addr = INADDR_ANY; // Cualquier cliente puede conectarse

    /**********************REALIZAR EL BIND**********************/
    if(bind(skt, (struct sockaddr*)&server, sizeof(server)) == SOCKET_ERROR){
        printf("Error al realizar el bind\n");
        exit(-1);
    }
    printf("Bind realizado\n");
    listen(skt, 5);


    while(true){
        printf("Esperando conexiones entrantes...\n");
        longitud_cliente = sizeof(struct sockaddr_in);
        skt2 = accept(skt, (struct sockaddr*)&cliente, &longitud_cliente);
        if(skt2 == INVALID_SOCKET){
            printf("Fallo al aceptar la conexion\n");
            exit(-1);
        }

        system("cls");
        printf("Cliente %s conectado exitosamente\n", inet_ntoa(cliente.sin_addr));
        handle_connection(skt2);
        //closesocket(skt);

    }


    closesocket(skt);
    WSACleanup();

    return 0;
}