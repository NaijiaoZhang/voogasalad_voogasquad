package engine;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import engine.observer.ObservableObjectProperty;
import engine.observer.ObservableProperty;

public abstract class AbstractTypeBuilder<E extends Type, R extends TypeBuilder<E, R>> implements TypeBuilder<E, R>, TypeInitializer {
    
    private final String DEFAULT_NAME;
    private final String DEFAULT_IMAGE_PATH;
    private final Double DEFAULT_SIZE;
    private final String DEFAULT_SOUND;
    
    
    private ObservableProperty<String> name;
    private ObservableProperty<String> imagePath;
    private ObservableProperty<Double> size;
    private ObservableProperty<String> sound;
    private int id;
    private int nextId;
    
    
    protected AbstractTypeBuilder(String name, String imagePath, double size, String sound) {
        this.DEFAULT_NAME = name;
        this.DEFAULT_IMAGE_PATH = imagePath;
        this.DEFAULT_SIZE = size;
        this.DEFAULT_SOUND = sound;
        this.nextId = 0;
        restoreDefaults();
    }
    
    @Override
    public R buildName (String name) {
        this.name.setProperty(name);
        return getThis();
    }
    
    @Override
    public R buildImagePath(String imagePath) {
        this.imagePath.setProperty(imagePath);
        return getThis();
    }
    
    @Override
    public R buildSize(double size) {
        this.size.setProperty(size);
        return getThis();
    }
    
    @Override
    public R buildSound(String soundPath) {
    	this.sound.setProperty(soundPath);
    	return getThis();
    }
    
    @Override
    public R buildId(int id) {
        this.id = id;
        return getThis();
    }
    
    @Override
    public E build () {
        E newEntity = create();
        nextId++;
        restoreDefaults();
        return newEntity;
    }
    
    @Override
    public ObservableProperty<String> getName () {
        return name;
    }

    @Override
    public ObservableProperty<String> getImagePath () {
        return imagePath;
    }

    @Override
    public ObservableProperty<Double> getSize () {
        return size;
    }
    
    @Override
    public ObservableProperty<String> getSound () {
    	return sound;
    }
    
    @Override
    public int getId() {
        return id;
    }
    
    @Override
    public boolean setNextId(int id) {
        boolean isValidIdAssignment = id > nextId;
        if(isValidIdAssignment) {
            nextId = id;
        }
        return isValidIdAssignment;
    }
    
    @Override
    public R addNameListener(BiConsumer<String, String> listener) {
        name.addListener(listener);
        return getThis();
    }
    
    @Override
    public R addImagePathListener(BiConsumer<String, String> listener) {
        imagePath.addListener(listener);
        return getThis();
    }
    
    @Override
    public R addSizeListener(BiConsumer<Double, Double> listener) {
        size.addListener(listener);
        return getThis();
    }
    
    @Override
    public R addSoundListener(BiConsumer<String, String> listener) {
    	sound.addListener(listener);
    	return getThis();
    }
    
    @Override
    public R copy(E type) {
        return copyType(type)
        .buildId(type.getId())
        .buildName(type.getName())
        .buildImagePath(type.getImagePath())
        .buildSize(type.getSize())
        .buildSound(type.getSound());
    }
    
    protected void resetName(ObservableProperty<String> name) {
        this.name = name;
    }
    
    protected void resetImagePath(ObservableProperty<String> imagePath) {
        this.imagePath = imagePath;
    }
    
    protected void resetSize(ObservableProperty<Double> size) {
        this.size = size;
    }
    
    public void createInputVariable(String name, Class<E> type) {
        Map<String, Class<?>> varmap = new HashMap<String, Class<?>>();
        varmap.put(name, type);
        ObservableProperty<E> test = new ObservableObjectProperty<E>(null);
        
    }
    
    private void restoreDefaults() {
        this.id = nextId;
        this.name = new ObservableObjectProperty<String>(DEFAULT_NAME + " (" + id + ")");
        this.imagePath = new ObservableObjectProperty<String>(DEFAULT_IMAGE_PATH);
        this.size = new ObservableObjectProperty<Double>(DEFAULT_SIZE);
        this.sound = new ObservableObjectProperty<String>(DEFAULT_SOUND);
        restoreTypeDefaults();
    }
    
    protected abstract E create ();
    
    protected abstract void restoreTypeDefaults();
    
    protected abstract R copyType(E type);
    
    protected abstract R getThis();

    
}
