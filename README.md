# Android High Performance Layout Inflater

> A class for inflating layouts asynchronously. To use, construct an instance of AsyncLayoutInflater on the UI thread and call inflate. 
> The OnInflateFinishedListener will be invoked on the UI thread when the inflate request has completed. And the cache the inflated view for specified id.

> This is intended for parts of the UI that are created lazily or in response to user interactions. 
> This allows the UI thread to continue to be responsive &animate while the relatively heavy inflate is being performed.


## 1. Usage

```java
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AsyncLayoutInflater mHighPerformanceInflater ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        mHighPerformanceInflater = new AsyncLayoutInflater(this);
        // 1. load and cache the will_inflate_layout.xml
        mHighPerformanceInflater.inflateAndCache(R.layout.will_inflate_layout, binding.getRoot());
        binding.loadLayoutBtn.setOnClickListener((v) -> {
            // 2. take cached view from ViewCache with specified view id
            final View cachedView = mHighPerformanceInflater.getViewCache().take(R.layout.will_inflate_layout);
            if ( cachedView != null ) {
                // 3. add cachedView to view hierarchy
                binding.viewContainer.addView(cachedView);
            }
        });
    }
}
```