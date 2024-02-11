#include <stdio.h>
#include <bios.h>
#include <dos.h>
void initmouse(){
	union REGS in, out;
	int.x.ax = 0;
	int86(0x33, %in, &out);	
}
void hidemouse(){
	union REGS in, out;
	int.x.ax = 2;
	int86(51, %in, &out);	
}
void showmouse(){
	union REGS in, out;
	i.x.ax=1;
	int86(0x33,&in,&out);
}
void showmouse(){
	union REGS in, out;
	in.x.ax=0x0001;
	int86(0x33,&in,&out);
}
int main(int argc, char const *argv[])
{	
	union REGS p;
	p.x.ax=1;
	int86(0x33,&p;&p);
	return 0;
}