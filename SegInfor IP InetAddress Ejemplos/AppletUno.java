import java.awt.*;
import java.applet.*;
public class UnApplet extends Applet{
	int cuenta;
	public void init(){
		cuenta = 0;
	}
	public void paint(Graphics g){
		g.drawString("Este es un Applet", 25, 25);
		++cuenta;
		g.drawString("Veces de llamadas a Paint:" + cuenta, 25, 50);
	}
}

/* <applet code=UnApplet.class width=100 height=100> </applet> */

/**
<applet [archivo=archivoList]
	code=appletFile.class
	width=pixeles height=pixeles
	[codebase=codebaseURL]
	[alt=textoAlternativo]
	[name=nombreDeInstanciaDelApplet]
	[align=alineamiento]
	[vspace=pixeles] [hspace=pixeles]
>
[<param name=atributo1 value=valor]
[<param name=atributo2 value=valor]
	:
[alternativoHTML]
</applet>
*/