package nrabello.back.core.usecase;

public interface IUseCase<I,O> {

    O execute(I input);
}
