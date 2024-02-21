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

public class PersonaSinAdapter extends RecyclerView.Adapter<PersonaSinAdapter.ViewHolder> {
    private List<Persona> listaPersona;

    public PersonaSinAdapter(List<Persona> listaPersona){this.listaPersona = listaPersona;}

    @NonNull
    @Override
    public PersonaSinAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_lista_vendedores_sin, parent,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaSinAdapter.ViewHolder holder, int position){
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
        Button btnAsignar;
        public ViewHolder(View itemView){
            super(itemView);
            contexto = itemView.getContext();
            documento = itemView.findViewById(R.id.etqDocumento);
            nombre = itemView.findViewById(R.id.etqNombre);
            apellido = itemView.findViewById(R.id.etqApellido);
            btnInfo = itemView.findViewById(R.id.btnInfoVendedor);
            btnEditarVendedor = itemView.findViewById(R.id.btnEditarVendedor);
            btnAsignar = itemView.findViewById(R.id.btnAsignar);
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

            btnAsignar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Btn Asignar Vendedores");
                }
            });
        }
    }
}
