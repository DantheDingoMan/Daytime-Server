import socket
from socket import SOCK_STREAM, AF_INET, SOCK_DGRAM
import argparse
import time
from time import sleep
from random import randint

host = "127.0.0.1"
port = 13

def tcp():
    
    servertcp = socket.socket(AF_INET, SOCK_STREAM)
    servertcp.bind((host, port))
    servertcp.listen(5)
    
    sock, addr = servertcp.accept()
        
    daytimeproc = time.strftime("%A, %B, %d, %Y, %H:%M:%S\n")
    sock.send(daytimeproc.encode("ascii"))
    servertcp.close()
    

def udp():

    serverudp = socket.socket(AF_INET, SOCK_DGRAM)

    while True:
        try:
            serverudp.bind((host, port))
            break
        except OSError as os_err:
            sleep(randint(1, 5))
    
    request, client = serverudp.recvfrom(2048)

    daytimeproc = time.strftime("%A, %B, %d, %Y, %H:%M:%S\n")
    serverudp.sendto(daytimeproc.encode("ascii"), client)

    serverudp.close()



def main():

    arg_parser = argparse.ArgumentParser(description="Parse arguments")

    arg_parser.add_argument("protocol")
    args = arg_parser.parse_args()
    print(args)
    if args.protocol == 'TCP' or args.protocol == 'tcp':
        tcp()
    if args.protocol == 'UDP' or args.protocol == 'udp':
        udp()
    
if __name__ == "__main__":
    main()
