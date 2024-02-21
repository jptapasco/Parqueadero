package com.example.parqueadero.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parqueadero.R;
import org.w3c.dom.Text;
import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.ViewHolder> {
    private List<Persona> listaPersona;

    public PersonaAdapter(List<Persona> listaPersona){this.listaPersona = listaPersona;}

    @NonNull
    @Override
    public PersonaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_lista_vendedores, parent,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaAdapter.ViewHolder holder, int position){
        Persona persona = listaPersona.get(position);
        holder.cargarPersona(persona);
    }

    @Override
    public int getItemCount(){return listaPersona.size();}

    public class ViewHolder extends RecyclerView.ViewHolder{
        Context contexto;
        TextView documento;
        TextView nombre;
        TextView apellido;
        Button btnInfo;
        Button btnEditarVendedor;
        Button btnDesligarAsignar;
        public ViewHolder(View itemView){
            super(itemView);
            contexto = itemView.getContext();
            documento = itemView.findViewById(R.id.etqDocumento);
            nombre = itemView.findViewById(R.id.etqNombre);
            apellido = itemView.findViewById(R.id.etqApellido);
            btnInfo = itemView.findViewById(R.id.btnInfoVendedor);
            btnEditarVendedor = itemView.findViewById(R.id.btnEditarVendedor);
            btnDesligarAsignar = itemView.findViewById(R.id.btn_Desligar_Asignar);
        }

        public void cargarPersona(Persona persona){
            documento.setText(persona.documento);
            nombre.setText(persona.nombre);
            apellido.setText(persona.apellido);

            btnInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Btn info Vendedores");
                }
            });

            btnEditarVendedor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Btn Editar Vendedores");
                }
            });

            btnDesligarAsignar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Btn Desligar Vendedores");
                }
            });
        }
    }
}
