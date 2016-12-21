package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import authoring.editorview.IUpdateView;


public abstract class AbstractTypeManagerController<E extends Manager<T>, U extends TypeBuilder<T, U>, T extends Type, V extends IUpdateView>
        implements ManagerController<E, U, T, V> {

    private E typeManager;
    private U typeBuilder;

    protected AbstractTypeManagerController (E typeManager,
                                             U typeBuilder,
                                             ManagerMediator managerMediator) {
        this.typeManager = typeManager;
        this.typeBuilder = typeBuilder;
        managerMediator.addManager(typeManager);
        typeManager.addObserver(managerMediator);
        // typeManager.addEntry(typeBuilder.build()); //Testing XML
    }

    @Override
    public void loadManagerData(E typeManager, V updateView) {
        //this.typeManager = typeManager;
        this.typeManager.setEntities(typeManager.getEntities().keySet().stream().collect(Collectors.toMap(b -> b , b -> constructCopy(b, typeManager, updateView))));
//    	Map<Integer, T> newMap = new HashMap<Integer, T>();
//        for (Integer id : typeManager.getEntities().keySet()) {
//        	T value = constructCopy(id, typeManager, updateView);
//        	newMap.put(id, value);
//        }
//        this.typeManager.setEntities(newMap);
        typeBuilder.setNextId(this.typeManager.getMaxId());
        //call some method that resets views
    }
    
    @Override
    public int createType (V updateView) {
        return typeManager.addEntry(constructType(updateView));
    }
    
    @Override //TODO - remove duplicated code
    public int createCopy(int id, V updateView) {
        copyWithoutId(id).buildId(typeManager.getEntity(id).getId());
        return createType(updateView);
    }
    
    protected U copyWithoutId(int id) {
        return typeBuilder.copy(typeManager.getEntity(id));
    }
    
    protected T constructCopy(int id, E typeManager, V updateView) {
        typeBuilder.copy(typeManager.getEntity(id));
        return constructType(updateView);
    }

    protected T constructType (V updateView) {
        return constructTypeProperties(updateView, typeBuilder)
                .addNameListener( (oldValue, newValue) -> updateView
                        .updateNameDisplay(newValue))
                .addImagePathListener( (oldValue, newValue) -> updateView
                        .updateImagePathDisplay(newValue))
                .addSizeListener( (oldValue, newValue) -> updateView
                        .updateSizeDisplay(newValue))
                //.addSoundListener(listener) need method in front end to call
                .build();
    }
    
    @Override
    public void addTypeBankListener(V updateView) {
        typeManager.addEntitiesListener((oldValue, newValue) -> {
        	updateView.updateBank(new ArrayList<Integer>(newValue.keySet().isEmpty() ? new ArrayList<Integer>() : newValue.keySet()));
        });
    }

    @Override
    public void deleteType (int id) {
        typeManager.removeEntry(id);
    }

    @Override
    public String getName (int id) {
        return typeManager.getEntity(id).getName();
    }

    @Override
    public String getImagePath (int id) {
        return typeManager.getEntity(id).getImagePath();
    }

    @Override
    public Double getSize (int id) {
        return typeManager.getEntity(id).getSize();
    }

    @Override
    public String getSound (int id) {
    	return typeManager.getEntity(id).getSound();
    }
    
    @Override
    public List<Integer> getCreatedTypeIds () {
        return typeManager.getEntityIds();
    }

    //TODO - is this ok?
    @Override
    public boolean setName (int id, String name) {
        return handleRequest(isUnique(Type::getName, name), a -> a.getEntity(id).setName(name));
    }

    protected boolean handleRequest(boolean isValid, Consumer<E> request) {
        if(isValid) {
            request.accept(typeManager);
        }
        return isValid;
    }
    
    @Override
    public void setImagePath (int id, String imagePath) {
    	String oldPath = this.getImagePath(id);
//    	imagePath = FileAggregator.defaultInstance().addImageToAssets(oldPath, imagePath);
        typeManager.getEntity(id).setImagePath(imagePath);
    }

    @Override
    public void setSize (int id, double size) {
        typeManager.getEntity(id).setSize(size);
    }
    
    @Override
    public void setSound (int id, String soundPath) {
    	typeManager.getEntity(id).setSound(soundPath);
    }
    
    protected E getTypeManager () {
        return typeManager;
    }

    // TODO - try and not need this
    protected U getTypeBuilder () {
        return typeBuilder;
    }

    protected abstract U constructTypeProperties (V updateView, U typeBuilder);

//    protected interface ViewFiller {
//        void updateNameDisplay (String name);
//
//        void updateImagePathDisplay (String imagePath);
//
//        void updateSizeDisplay (double size);
//    }
    
    protected <R> boolean isUnique(Function<T, R> getter, R value) {
        return !typeManager.getEntityIds().stream().map(a -> typeManager.getEntity(a)).anyMatch(b -> getter.apply(b).equals(value));
    }
}
