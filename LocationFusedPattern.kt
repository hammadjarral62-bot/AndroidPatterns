// FUSED LOCATION PROVIDER (SINGLE LOCATION)

private val LOCATION_PERMISSION = 101
private lateinit var fusedLocationClient: FusedLocationProviderClient

// Initialize
fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

private fun checkPermission(): Boolean {
    return ActivityCompat.checkSelfPermission(
        this, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

private fun requestPermission() {
    ActivityCompat.requestPermissions(
        this,
        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
        LOCATION_PERMISSION
    )
}

private fun getCurrentLocation() {
    if (!checkPermission()) {
        requestPermission()
        return
    }

    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
        if (location != null) {
            val lat = location.latitude
            val lon = location.longitude

            Toast.makeText(this, "Lat: $lat, Lon: $lon", Toast.LENGTH_LONG).show()
        }
    }
}

override fun onRequestPermissionsResult(
    requestCode: Int, permissions: Array<out String>, grantResults: IntArray
) {
    if (requestCode == LOCATION_PERMISSION &&
        grantResults.isNotEmpty() &&
        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        getCurrentLocation()
    }
}
