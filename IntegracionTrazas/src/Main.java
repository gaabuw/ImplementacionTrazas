import entidades.*;
import repositorios.InMemoryRepository;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //////////TRAZA 1
        //Inicializamos el repositorio inMemoryRepository
        InMemoryRepository<Empresa> empresaRepository = new InMemoryRepository<>();
        System.out.println("~~~~~~~~~~ SISTEMA INICIADO ~~~~~~~~~~");
        System.out.println("");
        System.out.println("-------------- TRAZA 1 -----------------");

        //Creamos un país
        Pais argentina = Pais.builder().nombre("Argentina")
                .build();
        //Creamos dos provincias
        Provincia buenosAires = Provincia.builder()
                .id(1L)
                .nombre("Buenos Aires")
                .pais(argentina) //relacionamos con el pais
                .build();
        Provincia cordoba = Provincia.builder()
                .id(2L)
                .nombre("Córdoba")
                .pais(argentina) //relacionamos con el pais
                .build();
        //Creamos localidades para cada provincia
        Localidad caba = Localidad.builder()
                .id(1L)
                .nombre("Caba")
                .provincia(buenosAires) //relacionamos con Baires
                .build();
        Localidad laPlata = Localidad.builder()
                .id(2L)
                .nombre("La Plata")
                .provincia(buenosAires) //relacionamos con Baires
                .build();
        Localidad cordobaCapital = Localidad.builder()
                .id(3L)
                .nombre("Córdoba Capital")
                .provincia(cordoba)
                .build();
        Localidad villaCarlosPaz = Localidad.builder()
                .id(4L)
                .nombre("Villa Carlos Paz")
                .provincia(cordoba)
                .build();
        //Creamos domicilios
        Domicilio dom1 = Domicilio.builder()
                .id(1L)
                .calle("Rodriguez")
                .numero(256)
                .cp(1001)
                .localidad(caba)
                .build();
        Domicilio dom2 = Domicilio.builder()
                .id(2L)
                .calle("Belgrano")
                .numero(102)
                .cp(1049)
                .localidad(laPlata)
                .build();
        Domicilio dom3 = Domicilio.builder()
                .id(3L)
                .calle("Lugones")
                .numero(23)
                .cp(5014)
                .localidad(cordobaCapital)
                .build();
        Domicilio dom4 = Domicilio.builder()
                .id(4L)
                .calle("Rodriguez")
                .numero(341)
                .cp(2473)
                .localidad(villaCarlosPaz)
                .build();

        //Sucursales para Baires
        Sucursal s1 = Sucursal.builder()
                .id(1L)
                .nombre("Sucursal 1")
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(18, 0))
                .es_Casa_Matriz(true)
                .domicilio(dom1)
                .build();
        Sucursal s2 = Sucursal.builder()
                .id(2L)
                .nombre("Sucursal 2")
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(18, 0))
                .es_Casa_Matriz(false)
                .domicilio(dom2)
                .build();
        //Sucursales para Cordoba
        Sucursal s3 = Sucursal.builder()
                .id(3L)
                .nombre("Sucursal 3")
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(18, 0))
                .es_Casa_Matriz(true)
                .domicilio(dom3)
                .build();
        Sucursal s4 = Sucursal.builder()
                .id(4L)
                .nombre("Sucursal 4")
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(18, 0))
                .es_Casa_Matriz(false)
                .domicilio(dom4)
                .build();

        //Creamos empresas y asociamos sucursales
        Empresa empresa1 = Empresa.builder()
                .nombre("Empresa 1")
                .razonSocial("Razon Social 1")
                .cuit(301231234)
                .logo("imagenes/empresa1.png")
                .sucursales(new HashSet<>(Set.of(s1, s2)))
                .build();
        Empresa empresa2 = Empresa.builder()
                .nombre("Empresa 2")
                .razonSocial("Razon Social 2")
                .cuit(275566889)
                .logo("imagenes/empresa2.png")
                .sucursales(new HashSet<>(Set.of(s3, s4)))
                .build();

        //Asignamos empresa a sucursales para la bidireccionalidad
        s1.setEmpresa(empresa1);
        s2.setEmpresa(empresa1);
        s3.setEmpresa(empresa2);
        s4.setEmpresa(empresa2);

        //Guardamos las empresas en el repositorio
        empresaRepository.save(empresa1);
        empresaRepository.save(empresa2);





        /////////////////TRAZA 2
        System.out.println("");
        System.out.println("-------------- TRAZA 2 -----------------");
        //Iniciamos repositorios
        InMemoryRepository<Categoria> categoriaRepository = new InMemoryRepository<>();
        InMemoryRepository<ArticuloInsumo> articuloInsumoRepository = new InMemoryRepository<>();
        InMemoryRepository<ArticuloManufacturado> articuloManufacturadoRepository = new InMemoryRepository<>();
        InMemoryRepository<UnidadMedida> unidadMedidaRepository = new InMemoryRepository<>();

        //Creamos Categorias y guardamos
        Categoria pizzas = Categoria.builder().denominacion("Pizzas").esInsumo(false).build();
        Categoria lomos = Categoria.builder().denominacion("Lomos").esInsumo(false).build();
        Categoria insumos = Categoria.builder().denominacion("Insumos").esInsumo(true).build();

        categoriaRepository.save(pizzas);
        categoriaRepository.save(lomos);
        categoriaRepository.save(insumos);

        //Creamos Unidad de Medida y guardamos
        UnidadMedida kg = UnidadMedida.builder().denominacion("Kg").id(1L).build();
        UnidadMedida gramos = UnidadMedida.builder().denominacion("Gramos").id(2L).build();
        UnidadMedida litros = UnidadMedida.builder().denominacion("Litros").id(3L).build();

        unidadMedidaRepository.save(kg);
        unidadMedidaRepository.save(gramos);
        unidadMedidaRepository.save(litros);

        //Creamos Articulos Insumos y guardamos
        ArticuloInsumo sal = ArticuloInsumo.builder()
                .denominacion("Sal")
                .precioCompra(10.0)
                .precioVenta(11.0)
                .stockActual(100)
                .stockMaximo(200)
                .esParaElaborar(true)
                .categoria(insumos)
                .unidadMedida(gramos)
                .build();
        ArticuloInsumo aceite = ArticuloInsumo.builder()
                .denominacion("Aceite")
                .precioCompra(15.0)
                .stockActual(30)
                .stockMaximo(50)
                .esParaElaborar(true)
                .categoria(insumos)
                .unidadMedida(litros)
                .build();
        ArticuloInsumo carne = ArticuloInsumo.builder()
                .denominacion("Carne")
                .precioCompra(20.0)
                .precioVenta(21.0)
                .stockActual(80)
                .stockMaximo(100)
                .esParaElaborar(true)
                .categoria(insumos)
                .unidadMedida(kg)
                .build();
        ArticuloInsumo harina = ArticuloInsumo.builder()
                .denominacion("Harina")
                .precioCompra(10.0)
                .precioVenta(12.0)
                .stockActual(20)
                .stockMaximo(100)
                .esParaElaborar(true)
                .categoria(insumos)
                .unidadMedida(kg)
                .build();

        articuloInsumoRepository.save(sal);
        articuloInsumoRepository.save(aceite);
        articuloInsumoRepository.save(carne);
        articuloInsumoRepository.save(harina);

        //Creamos Imagenes
        Imagen img1 = Imagen.builder().denominacion("HawainaPizza1").build();
        Imagen img2 = Imagen.builder().denominacion("HawainaPizza2").build();
        Imagen img3 = Imagen.builder().denominacion("HawainaPizza3").build();

        Imagen img4 = Imagen.builder().denominacion("LomoCompleto1").build();
        Imagen img5 = Imagen.builder().denominacion("LomoCompleto2").build();
        Imagen img6 = Imagen.builder().denominacion("LomoCompleto3").build();

        //Creamos detalle de los Articulos Manufacturados
        //Asignamos un insumo por detalle
        ArticuloManufacturadoDetalle detallePizzaHawaina1 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1)
                .articuloInsumo(sal)
                .build();
        ArticuloManufacturadoDetalle detallePizzaHawaina2 = ArticuloManufacturadoDetalle.builder()
                .cantidad(2)
                .articuloInsumo(harina)
                .build();
        ArticuloManufacturadoDetalle detallePizzaHawaina3 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1)
                .articuloInsumo(aceite)
                .build();

        ArticuloManufacturadoDetalle detalleLomoCompleto1 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1)
                .articuloInsumo(sal)
                .build();
        ArticuloManufacturadoDetalle detalleLomoCompleto2 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1)
                .articuloInsumo(aceite)
                .build();
        ArticuloManufacturadoDetalle detalleLomoCompleto3 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1)
                .articuloInsumo(carne)
                .build();

        //Creamos los articulos Manufacturados y guardamos
        ArticuloManufacturado pizzaHawaina = ArticuloManufacturado.builder()
                .denominacion("Pizza Hawaina")
                .descripcion("Pizza con piña y jamón")
                .precioVenta(300.0)
                .tiempoEstimadoMinutos(25)
                .preparacion("Hornear por 25 minutos")
                .categoria(pizzas)
                .unidadMedida(kg)
                .imagenes(new HashSet<>(Set.of(img1,img2,img3)))
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detallePizzaHawaina1,detallePizzaHawaina2,detallePizzaHawaina3)))
                .build();

        ArticuloManufacturado lomoCompleto = ArticuloManufacturado.builder()
                .denominacion("Lomo Completo")
                .descripcion("Lomo completo con todos los ingredientes")
                .precioVenta(250.0)
                .tiempoEstimadoMinutos(30)
                .preparacion("Cocinar por 30 minutos")
                .categoria(lomos)
                .unidadMedida(kg)
                .imagenes(new HashSet<>(Set.of(img4,img5,img6)))
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detalleLomoCompleto1,detalleLomoCompleto2,detalleLomoCompleto3)))
                .build();

        articuloManufacturadoRepository.save(pizzaHawaina);
        articuloManufacturadoRepository.save(lomoCompleto);


        //TRAZAS UNIDAS
        System.out.println("");
        System.out.println("-------------- TRAZAS UNIDAS -----------------");
        InMemoryRepository<SucursalArticulo> sucursalArticuloRepository = new InMemoryRepository<>();

        // Asociar articulos a sucursal
        s1.getCategorias().add(pizzas);
        s2.getCategorias().add(lomos);

        s3.getCategorias().add(insumos);
        s4.getCategorias().add(lomos);


        //Actualizar en repositorio para mantener consistencia
        empresaRepository.genericUpdate(empresa1.getId(), empresa1);
        empresaRepository.genericUpdate(empresa2.getId(), empresa2);

        //Pizza Hawaina en Sucursal 1
        SucursalArticulo sa1 = SucursalArticulo.builder()
                .sucursal(s1)
                .articulo(pizzaHawaina)
                .stockActual(10)
                .stockMaximo(20)
                .build();

        //Lomo Completo en Sucursal 2
        SucursalArticulo sa2 = SucursalArticulo.builder()
                .sucursal(s2)
                .articulo(lomoCompleto)
                .stockActual(15)
                .stockMaximo(25)
                .build();

        //Insumos en Sucursal 3
        SucursalArticulo sa3sal = SucursalArticulo.builder()
                .sucursal(s3)
                .articulo(sal)
                .stockActual(30)
                .stockMaximo(40)
                .build();
        SucursalArticulo sa3harina = SucursalArticulo.builder()
                .sucursal(s3)
                .articulo(harina)
                .stockActual(20)
                .stockMaximo(30)
                .build();
        SucursalArticulo sa3aceite = SucursalArticulo.builder()
                .sucursal(s3)
                .articulo(aceite)
                .stockActual(50)
                .stockMaximo(20)
                .build();

        //Lomo Completo en Sucursal 4
        SucursalArticulo sa4 = SucursalArticulo.builder()
                .sucursal(s4)
                .articulo(lomoCompleto)
                .stockActual(18)
                .stockMaximo(20)
                .build();

        //Guardar en repositorio
        sucursalArticuloRepository.save(sa1);
        sucursalArticuloRepository.save(sa2);
        sucursalArticuloRepository.save(sa4);

        sucursalArticuloRepository.save(sa3aceite);
        sucursalArticuloRepository.save(sa3sal);
        sucursalArticuloRepository.save(sa3harina);

        //Mostramos las empresas
        System.out.println("\nLista de Empresas:");
        List<Empresa> empresaList = empresaRepository.findAll();
        empresaList.forEach(System.out::println);

        // Mostrar todas las categorías
        System.out.println("\nTodas las categorías:");
        List<Categoria> todasLasCategorias = categoriaRepository.findAll();
        todasLasCategorias.forEach(System.out::println);
        System.out.println("");

        //Todos los artículos de Sucursal 1 y 2
        System.out.println("Articulos de la Sucursal 1:");
        List<SucursalArticulo> stockSucursal1 = sucursalArticuloRepository.genericFindByField("sucursal", s1);
        stockSucursal1.forEach(sa -> System.out.println("-" + sa.getArticulo().getDenominacion() + " - Stock: " + sa.getStockActual())
        );

        System.out.println("Articulos de la Sucursal 2:");
        List<SucursalArticulo> stockSucursal2 = sucursalArticuloRepository.genericFindByField("sucursal", s2);
        stockSucursal2.forEach(sa -> System.out.println("-" + sa.getArticulo().getDenominacion() + " - Stock: " + sa.getStockActual())
        );

        System.out.println("Articulos de la Sucursal 3:");
        List<SucursalArticulo> stockSucursal3 = sucursalArticuloRepository.genericFindByField("sucursal", s3);
        stockSucursal3.forEach(sa -> System.out.println("-" + sa.getArticulo().getDenominacion() + " - Stock: " + sa.getStockActual())
        );

        System.out.println("");
        //Buscar un artículo en particular en cualquier sucursal
        List<SucursalArticulo> pizzaEnSucursales = sucursalArticuloRepository.genericFindByField("articulo", pizzaHawaina);
        pizzaEnSucursales.forEach(sa -> System.out.println("Sucursal: " + sa.getSucursal().getNombre() + " - Pizza Hawaina Stock: " + sa.getStockActual()+ " - Precio $" + sa.getArticulo().getPrecioVenta())
        );
        List<SucursalArticulo> lomoEnSucursales = sucursalArticuloRepository.genericFindByField("articulo", lomoCompleto);
        lomoEnSucursales.forEach(sa -> System.out.println("Sucursal: " + sa.getSucursal().getNombre() + " - Lomo Completo Stock: " + sa.getStockActual() + " - Precio $" + sa.getArticulo().getPrecioVenta())
        );
        List<SucursalArticulo> salEnSucursales = sucursalArticuloRepository.genericFindByField("articulo", sal);
        salEnSucursales.forEach(sa -> System.out.println("Sucursal: " + sa.getSucursal().getNombre() + " - Sal Stock: " + sa.getStockActual() +  " - Precio $"+sa.getArticulo().getPrecioVenta())
        );
        List<SucursalArticulo> harinaEnSucursales = sucursalArticuloRepository.genericFindByField("articulo", harina);
        harinaEnSucursales.forEach(sa -> System.out.println("Sucursal: " + sa.getSucursal().getNombre() + " - Harina Stock: " + sa.getStockActual() + " - Precio $"+sa.getArticulo().getPrecioVenta())
        );
    }
}