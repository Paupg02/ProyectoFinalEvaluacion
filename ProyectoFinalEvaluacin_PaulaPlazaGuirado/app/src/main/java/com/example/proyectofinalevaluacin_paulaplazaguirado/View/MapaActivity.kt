package com.example.proyectofinalevaluacin_paulaplazaguirado.View

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.OnFragmentActionListener
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.Prefs
import com.example.proyectofinalevaluacin_paulaplazaguirado.R
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.ActivityMapaBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.firebase.auth.FirebaseAuth

class MapaActivity : AppCompatActivity() , OnMapReadyCallback, OnFragmentActionListener {

    lateinit var binding: ActivityMapaBinding
    lateinit var mapa:GoogleMap
    val LOCAL_PER_CODE=7
    lateinit var prefs: Prefs
    var email=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMapaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs= Prefs(this)
        crearFragment()
        getdatos()
    }

    private fun getdatos() {
        email= intent.getStringExtra("EMAIL").toString()
        prefs.guardarEmail(email)
    }

    private fun crearFragment() {
        val mapFragment= supportFragmentManager.findFragmentById(R.id.map)as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        mapa=p0
        mapa.uiSettings.isZoomControlsEnabled=true
        crearMarcadores()
        enableLocation()
        crearArea()
    }

    private fun crearArea() {
        val circuloOptions = CircleOptions()
            .center(LatLng(36.837378090096834, -2.459742692009836))
            .radius(5000.0)
        val circulo = mapa.addCircle(circuloOptions)
        val colorLinea=ContextCompat.getColor(this,R.color.verdeO)
        circulo.strokeColor=colorLinea
        val colorArea=ContextCompat.getColor(this, R.color.verdeT)
        circulo.fillColor=colorArea
    }

    @SuppressLint("MissingPermission")
    private fun enableLocation() {
        if (!::mapa.isInitialized)return
        if (isPermisosConcedidos()){
            mapa.isMyLocationEnabled=true
        }else{
            pedirPermisos()
        }
    }
    @SuppressLint("MissingPermission")
    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!isPermisosConcedidos()){
            if (!::mapa.isInitialized)return
            mapa.isMyLocationEnabled=false
            Toast.makeText(this,getString(R.string.permissionsL2), Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("MissingPermission", "MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            LOCAL_PER_CODE->{
                if (grantResults.isNotEmpty()&&(grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED)){
                    mapa.isMyLocationEnabled=true
                }else{
                    Toast.makeText(this,getString(R.string.permissionsL3),Toast.LENGTH_SHORT).show()
                }
            }
            else->{

            }
        }
    }

    private fun isPermisosConcedidos(): Boolean {
        return(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
    }

    private fun pedirPermisos() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
            || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)){
            Toast.makeText(this,getString(R.string.permissionsL), Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION),LOCAL_PER_CODE)
        }
    }

    private fun crearMarcadores() {
        val cord= LatLng(36.837378090096834, -2.459742692009836)
        val marcador= MarkerOptions().position(cord).title(getString(R.string.marcadorL))
        mapa.addMarker(marcador)
        mapa.animateCamera(
            CameraUpdateFactory.newLatLngZoom(cord,15f),6000, null
        )
    }

    override fun onClickMenu(btn: Int) {
        when(btn){
            0-> {
                Toast.makeText(this,getString(R.string.fragmentThere),Toast.LENGTH_LONG).show()
            }
            1-> {
                val i =Intent(this, CompraActivity::class.java).apply {
                    putExtra("EMAIL", email)
                }
                startActivity(i)
            }
            2-> {
                val i =Intent(this, PaquetesActivity::class.java).apply {
                    putExtra("EMAIL", email)
                }
                startActivity(i)
            }
            3->{
                prefs.borrarTodo()
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

}